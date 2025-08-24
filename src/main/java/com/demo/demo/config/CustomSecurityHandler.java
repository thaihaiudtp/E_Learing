package com.demo.demo.config;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.demo.demo.common.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSecurityHandler implements AuthenticationEntryPoint, AccessDeniedHandler{
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authenticationException        
    ) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        ApiResponse<Object> body = ApiResponse.error(
            401,
            "Unauthorized",
            new ErrorDetail("token", "Token không hợp lệ hoặc thiếu")
        );
        response.getWriter().write(mapper.writeValueAsString(body));
    }
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        ApiResponse<Object> body = ApiResponse.error(403, "Forbidden",
                new ErrorDetail("permission", "Bạn không có quyền truy cập"));

        response.getWriter().write(mapper.writeValueAsString(body));
    }

    record ErrorDetail(String field, String reason) {}

}
