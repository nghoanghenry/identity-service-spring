package com.nmhoang.identity_service.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmhoang.identity_service.dto.response.ApiResponse;
import com.nmhoang.identity_service.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        response.setStatus(errorCode.getHttpStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .build();

        ObjectMapper mapper = new ObjectMapper();

        response.getWriter().write(mapper.writeValueAsString(apiResponse));
        response.flushBuffer();
    }
}
