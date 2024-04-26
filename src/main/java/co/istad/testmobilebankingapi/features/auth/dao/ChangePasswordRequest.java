package co.istad.testmobilebankingapi.features.auth.dao;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
    @NotBlank(message = "Old password is required")
    String oldPassword,
    @NotBlank(message = "Password is required")
    String password,
    @NotBlank(message = "Confirmed password is required")
    String confirmedPassword
) {
}
