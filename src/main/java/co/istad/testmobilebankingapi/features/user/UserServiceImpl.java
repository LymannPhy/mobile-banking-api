package co.istad.testmobilebankingapi.features.user;

import co.istad.testmobilebankingapi.base.BasedMessage;
import co.istad.testmobilebankingapi.domain.Role;
import co.istad.testmobilebankingapi.domain.User;
import co.istad.testmobilebankingapi.features.user.dto.*;
import co.istad.testmobilebankingapi.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${media.base-uri}")
    private String mediaBaseUri;
    @Override
    public void createUser(UserCreateRequest userCreateRequest) {
        if (userRepository.existsByPhoneNumber(userCreateRequest.phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number has already been existed!"
            );
        }

        if (userRepository.existsByNationalCardId(userCreateRequest.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National card ID has already been existed!"
            );
        }

        if (userRepository.existsByStudentIdCard(userCreateRequest.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student card ID has already been existed!"
            );
        }

        if (!userCreateRequest.password()
                .equals(userCreateRequest.confirmedPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password doesn't match!"
            );
        }

        User user = userMapper.fromUserCreateRequest(userCreateRequest);
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProfileImage("avatar.png");
        user.setCreatedAt(LocalDateTime.now());
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);

        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Role USER has not been found!"));
        //Create dynamic role from client
        userCreateRequest.roles().forEach(r-> {
            Role newRole = roleRepository.findByName(r.name())
            .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Role USER has not been found!"));
            roles.add(newRole);
        });
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
    }
    @Override
    public void changePassword(UserChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findById(changePasswordRequest.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password and confirm password don't match");
        }

        if (!user.getPassword().equals(changePasswordRequest.getOldPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect old password");
        }

        user.setPassword(changePasswordRequest.getNewPassword());
        userRepository.save(user);
    }
    @Override
    public void editUserProfile(UserEditProfileRequest editProfileRequest) {
        User user = userRepository.findById(editProfileRequest.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Map fields from editProfileRequest to user entity
        user.setCityOrProvince(editProfileRequest.getCityOrProvince());
        user.setKhanOrDistrict(editProfileRequest.getKhanOrDistrict());
        user.setSangkatOrCommune(editProfileRequest.getSangkatOrCommune());
        user.setVillage(editProfileRequest.getVillage());
        user.setStreet(editProfileRequest.getStreet());
        user.setEmployeeType(editProfileRequest.getEmployeeType());
        user.setPosition(editProfileRequest.getPosition());
        user.setCompanyName(editProfileRequest.getCompanyName());
        user.setMainSourceOfIncome(editProfileRequest.getMainSourceOfIncome());
        user.setMonthlyIncomeRange(editProfileRequest.getMonthlyIncomeRange());

        userRepository.save(user);
    }

    @Override
    public UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest) {
        //check uuid if exists
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "USER has not been found!"));

                log.info("Before update user: {} ", user);
                userMapper.fromUserUpdateRequest(userUpdateRequest, user);
                user = userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserDetailsResponse findByUuid(String uuid) {
        //check uuid if exists
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "USER has not been found!"));
        return userMapper.toUserDetailsResponse(user);
    }

    @Transactional
    @Override
    public BasedMessage blockByUuid(String uuid) {

        if (!userRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User has not been found!");
        }

        userRepository.blockByUuid(uuid);

        return new BasedMessage("User has been blocked");
    }

    @Override
    public void deleteByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                        "User has not been found!"));
        userRepository.delete(user);

    }

    @Override
    public void enableUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setIsDeleted(false); // Set isDeleted to false to enable user
        userRepository.save(user);
    }

    @Override
    public void disableUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setIsDeleted(true); // Set isDeleted to true to disable user
        userRepository.save(user);
    }

    @Override
    public Page<UserResponse> findList(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<User> users = userRepository.findAll(pageRequest);
        return users.map(userMapper::toUserResponse);

    }

    @Override
    public String updateProfileImage(String uuid, String mediaName) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User not found"));
        user.setProfileImage(mediaName);
        userRepository.save(user);
        return mediaBaseUri + "IMAGES/" + mediaName;
    }

}

