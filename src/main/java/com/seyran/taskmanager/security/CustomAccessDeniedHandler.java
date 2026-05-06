package com.seyran.taskmanager.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyran.taskmanager.dto.ApiResponse;
import jakarta.servlet.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .success(false)
                .message("Forbidden: You don't have permission")
                .data(null)
                .build();

        new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
    }
}