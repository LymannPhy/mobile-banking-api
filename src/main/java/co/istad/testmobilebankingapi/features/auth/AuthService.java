package co.istad.testmobilebankingapi.features.auth;

import co.istad.testmobilebankingapi.features.auth.dao.AuthResponse;
import co.istad.testmobilebankingapi.features.auth.dao.LoginRequest;
import co.istad.testmobilebankingapi.features.auth.dao.RefreshTokenRequest;

public interface AuthService {
    AuthResponse refresh(RefreshTokenRequest refreshTokenRequest);
    AuthResponse login(LoginRequest loginRequest);
}
