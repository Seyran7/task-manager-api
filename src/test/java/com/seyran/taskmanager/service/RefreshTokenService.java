package com.seyran.taskmanager.service;

import com.seyran.taskmanager.entity.RefreshToken;
import com.seyran.taskmanager.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    // 🔥 CREATE TOKEN
    public RefreshToken createToken(String username, String role) {

        RefreshToken token = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .username(username)
                .role(role)
                .expiryDate(LocalDateTime.now().plusDays(7)) // 7 gün
                .revoked(false)
                .build();

        return refreshTokenRepository.save(token);
    }

    // 🔥 VALIDATE TOKEN
    public RefreshToken validateToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if (refreshToken.isRevoked()) {
            throw new RuntimeException("Token revoked");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        return refreshToken;
    }

    // 🔥 REVOKE (LOGOUT)
    public void revokeToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }
}