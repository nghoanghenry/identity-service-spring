package com.nmhoang.identity_service.controller;

import com.nimbusds.jose.JOSEException;
import com.nmhoang.identity_service.dto.request.AuthenticationRequest;
import com.nmhoang.identity_service.dto.request.IntrospectRequest;
import com.nmhoang.identity_service.dto.request.LogoutRequest;
import com.nmhoang.identity_service.dto.request.RefreshRequest;
import com.nmhoang.identity_service.dto.response.ApiResponse;
import com.nmhoang.identity_service.dto.response.AuthenticationResponse;
import com.nmhoang.identity_service.dto.response.IntrospectResponse;
import com.nmhoang.identity_service.dto.response.UserResponse;
import com.nmhoang.identity_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .data(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> login(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        var result = authenticationService.introspect(introspectRequest);
        return ApiResponse.<IntrospectResponse>builder()
                .data(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);
        return ApiResponse.<Void>builder()
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest refreshRequest) throws ParseException, JOSEException {
        var refreshToken = authenticationService.refeshToken(refreshRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .data(refreshToken)
                .build();
    }
}
