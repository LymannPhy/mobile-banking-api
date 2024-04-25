package co.istad.testmobilebankingapi.features.auth.dao;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank(message = "Refresh Token is required")
        String refreshToken
) {
}
