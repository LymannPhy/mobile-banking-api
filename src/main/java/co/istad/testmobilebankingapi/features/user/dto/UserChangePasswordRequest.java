package co.istad.testmobilebankingapi.features.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserChangePasswordRequest {
    private Long userId; // Change the data type to match the user ID in your entity
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
