package co.istad.testmobilebankingapi.features.auth;

import co.istad.testmobilebankingapi.features.auth.dao.AuthResponse;
import co.istad.testmobilebankingapi.features.auth.dao.LoginRequest;
import co.istad.testmobilebankingapi.features.auth.dao.RefreshTokenRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthService authService;
    @PostMapping("/refresh")
    AuthResponse refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refresh(refreshTokenRequest);
    }
    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }
}
