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

    public RefreshToken createRefreshToken(String username){
        RefreshToken token =RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .username(username)
                .expiryDate(LocalDateTime.now().plusDays(7))
                .build();
        return refreshTokenRepository.save(token);
    }
    public RefreshToken validateToken(String token){
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(()->new RuntimeException("Refresh token not found"));
        if(LocalDateTime.now().isAfter(refreshToken.getExpiryDate())){
            refreshTokenRepository.delete(refreshToken);
        }
    }
}
