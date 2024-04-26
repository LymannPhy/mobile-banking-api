package co.istad.testmobilebankingapi.features.auth;

import co.istad.testmobilebankingapi.features.auth.dao.AuthResponse;
import co.istad.testmobilebankingapi.features.auth.dao.ChangePasswordRequest;
import co.istad.testmobilebankingapi.features.auth.dao.LoginRequest;
import co.istad.testmobilebankingapi.features.auth.dao.RefreshTokenRequest;
import org.springframework.security.oauth2.jwt.Jwt;

public interface AuthService {
    void changePassword(Jwt jwt, ChangePasswordRequest changePasswordRequest);
    AuthResponse refresh(RefreshTokenRequest refreshTokenRequest);
    AuthResponse login(LoginRequest loginRequest);
}
