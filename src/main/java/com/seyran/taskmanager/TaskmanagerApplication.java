package com.seyran.taskmanager;

import com.seyran.taskmanager.entity.RefreshToken;
import com.seyran.taskmanager.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class TaskmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskmanagerApplication.class, args);
    }

    @Service
    @RequiredArgsConstructor
    public static class RefreshTokenService {

        private final RefreshTokenRepository refreshTokenRepository;

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
        public void revokeToken(String token) {

            RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Refresh token not found"));

            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
        }
    }
}
