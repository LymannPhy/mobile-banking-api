package co.istad.testmobilebankingapi.features.user;

import co.istad.testmobilebankingapi.base.BasedMessage;
import co.istad.testmobilebankingapi.features.user.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void createUser(UserCreateRequest userCreateRequest);
    void changePassword(UserChangePasswordRequest changePasswordRequest);
    void editUserProfile(UserEditProfileRequest editProfileRequest);
    UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest);
    UserDetailsResponse findByUuid(String uuid);

    BasedMessage blockByUuid(String uuid);

    void deleteByUuid(String uuid);
    void enableUserByUuid(String uuid);
    void disableUserByUuid(String uuid);
    Page<UserResponse> findList(int page, int limit);
    String updateProfileImage(String uuid, String mediaName);
}
