package co.istad.testmobilebankingapi.features.token;

import co.istad.testmobilebankingapi.features.auth.dao.AuthResponse;
import org.springframework.security.core.Authentication;

public interface TokenService {

    AuthResponse createToken(Authentication auth);

    String createAccessToken(Authentication auth);

    String createRefreshToken(Authentication auth);

}

