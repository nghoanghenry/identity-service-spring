package com.nmhoang.identity_service.service;

import com.nmhoang.identity_service.dto.request.AuthenticationRequest;
import com.nmhoang.identity_service.entity.User;
import com.nmhoang.identity_service.exception.AppException;
import com.nmhoang.identity_service.exception.ErrorCode;
import com.nmhoang.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    public boolean authenticate(AuthenticationRequest authenticationRequest) {
        User user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
    }
}
