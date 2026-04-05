package com.seyran.taskmanager.service;

import com.seyran.taskmanager.entity.User;
import com.seyran.taskmanager.repository.UserRepository;
import com.seyran.taskmanager.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    public String register(String username, String password){
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        userRepository.save(user);
        return jwtService.generateToken(username);
    }
    public String login(String username, String password){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(username);
    }
}
