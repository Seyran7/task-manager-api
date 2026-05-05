package com.seyran.taskmanager.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyran.taskmanager.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence (HttpServletRequest request,
                          HttpServletResponse response,
                          AuthenticationException authException)throws IOException{
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        ApiResponse<?>apiResponse=ApiResponse.builder()
                .success(false)
                .message("Unauthorized: Invalid or missing token")
                .data(null).build();
        new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);

    }
}
