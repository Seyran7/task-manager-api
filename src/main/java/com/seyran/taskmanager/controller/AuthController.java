package com.seyran.taskmanager.controller;

import com.seyran.taskmanager.dto.AuthRequest;
import com.seyran.taskmanager.dto.AuthResponse;
import com.seyran.taskmanager.dto.RefreshRequest;
import com.seyran.taskmanager.entity.RefreshToken;
import com.seyran.taskmanager.security.JwtService;
import com.seyran.taskmanager.service.AuthService;
import com.seyran.taskmanager.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request){
        return authService.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/logout")
    public String logout(@RequestBody RefreshRequest request){
        refreshTokenService.revokeToken(request.getRefreshToken());
        return "Logged out successfully";
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest request){

        RefreshToken token = refreshTokenService.validateToken(request.getRefreshToken());

        String newAccessToken = jwtService.generateToken(
                token.getUsername(),
                token.getRole()
        );

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(request.getRefreshToken())
                .build();
    }
}