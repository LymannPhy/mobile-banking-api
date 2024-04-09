package co.istad.testmobilebankingapi.features.user;

import co.istad.testmobilebankingapi.base.BasedMessage;
import co.istad.testmobilebankingapi.base.BasedResponse;
import co.istad.testmobilebankingapi.features.user.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        userService.createUser(userCreateRequest);
    }
    @PutMapping("/change-password")
    public void changePassword(@Valid @RequestBody UserChangePasswordRequest changePasswordRequest) {
        userService.changePassword(changePasswordRequest);
    }
    @PutMapping("/edit-profile")
    public void editProfile(@Valid @RequestBody UserEditProfileRequest editProfileRequest) {
        userService.editUserProfile(editProfileRequest);
    }
    @PatchMapping("/{uuid}")
    UserResponse updateByUuid(@PathVariable String uuid,
                              @RequestBody UserUpdateRequest userUpdateRequest){
        return userService.updateByUuid(uuid, userUpdateRequest);
    }
    @GetMapping("/{uuid}")
    UserDetailsResponse findByUuid(@PathVariable String uuid){
        return userService.findByUuid(uuid);
    }

    @PutMapping("/{uuid}/block")
    BasedMessage blockByUuid(@PathVariable String uuid){
        return userService.blockByUuid(uuid);
    }
    @DeleteMapping("/{uuid}")
    void deleteByUuid(@PathVariable String uuid){
        userService.deleteByUuid(uuid);
    }

    @PutMapping("/enable/{uuid}")
    public void enableUser(@PathVariable String uuid) {
        userService.enableUserByUuid(uuid);
    }

    @PutMapping("/disable/{uuid}")
    public void disableUser(@PathVariable String uuid) {
        userService.disableUserByUuid(uuid);
    }

    @GetMapping
    Page<UserResponse> findList(
            @RequestParam (required = false, defaultValue = "0") int page,
            @RequestParam (required = false, defaultValue = "2") int limit){
        return userService.findList(page, limit);
    }

    @PutMapping("/{uuid}/profile-image")
    BasedResponse<?> updateProfileImage (@PathVariable String uuid, @RequestBody UserProfileImageRequest request){
        String newProfileImageUri = userService.updateProfileImage(uuid, request.mediaName());
        return BasedResponse.builder().payload(newProfileImageUri).build();
    }

}
