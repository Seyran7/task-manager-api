package com.seyran.taskmanager.service;

import com.seyran.taskmanager.dto.AuthRequest;
import com.seyran.taskmanager.dto.AuthResponse;
import com.seyran.taskmanager.entity.RefreshToken;
import com.seyran.taskmanager.entity.Role;
import com.seyran.taskmanager.entity.User;
import com.seyran.taskmanager.repository.UserRepository;
import com.seyran.taskmanager.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    public AuthResponse register(AuthRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String accessToken = jwtService.generateToken(
                user.getUsername(),
                user.getRole().name()
        );

        RefreshToken refreshToken = refreshTokenService.createToken(
                user.getUsername(),
                user.getRole().name()
        );

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public AuthResponse login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String accessToken = jwtService.generateToken(
                user.getUsername(),
                user.getRole().name()
        );

        RefreshToken refreshToken = refreshTokenService.createToken(
                user.getUsername(),
                user.getRole().name()
        );

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }
}