package co.istad.testmobilebankingapi.features.auth;

import co.istad.testmobilebankingapi.features.auth.dao.AuthResponse;
import co.istad.testmobilebankingapi.features.auth.dao.LoginRequest;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
}
