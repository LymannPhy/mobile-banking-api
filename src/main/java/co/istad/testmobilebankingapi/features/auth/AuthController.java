package co.istad.testmobilebankingapi.features.auth;

import co.istad.testmobilebankingapi.features.auth.dao.AuthResponse;
import co.istad.testmobilebankingapi.features.auth.dao.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/login")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthService authService;
    @PostMapping
    AuthResponse login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }
}
