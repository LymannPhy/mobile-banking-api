package co.istad.testmobilebankingapi.features.auth.dao;

public record AuthResponse(
        String type,
        String accessToken,
        String refreshToken
) {
}
