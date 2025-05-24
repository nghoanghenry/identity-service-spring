package com.nmhoang.identity_service.controller;

import com.nmhoang.identity_service.dto.request.AuthenticationRequest;
import com.nmhoang.identity_service.dto.response.ApiResponse;
import com.nmhoang.identity_service.dto.response.AuthenticationResponse;
import com.nmhoang.identity_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authen")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        boolean success = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .data(AuthenticationResponse.builder()
                        .authenticated(success)
                        .build())
                .build();
    }
}
