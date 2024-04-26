package co.istad.testmobilebankingapi.features.auth;

import co.istad.testmobilebankingapi.features.auth.dao.AuthResponse;
import co.istad.testmobilebankingapi.features.auth.dao.ChangePasswordRequest;
import co.istad.testmobilebankingapi.features.auth.dao.LoginRequest;
import co.istad.testmobilebankingapi.features.auth.dao.RefreshTokenRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthService authService;

    @PutMapping("change-password")
    void changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest,
                        @AuthenticationPrincipal Jwt jwt){
        authService.changePassword(jwt, changePasswordRequest);

    }

    @PostMapping("/refresh")
    AuthResponse refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refresh(refreshTokenRequest);
    }
    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }
}
