package com.nmhoang.identity_service.service;

import com.nmhoang.identity_service.dto.request.UserCreationRequest;
import com.nmhoang.identity_service.dto.request.UserUpdateRequest;
import com.nmhoang.identity_service.dto.response.UserResponse;
import com.nmhoang.identity_service.entity.User;
import com.nmhoang.identity_service.enums.Role;
import com.nmhoang.identity_service.exception.AppException;
import com.nmhoang.identity_service.exception.ErrorCode;
import com.nmhoang.identity_service.mapper.UserMapper;
import com.nmhoang.identity_service.repository.RoleRepository;
import com.nmhoang.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse createUser(UserCreationRequest userCreationRequest) {
        if(userRepository.existsByUsername(userCreationRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(userCreationRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        //user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        ));
    }

    public UserResponse updateUser(String id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, userUpdateRequest);

        user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        var roles = roleRepository.findAllById(userUpdateRequest.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));

    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public UserResponse getMyInfo(){
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(userName).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );
        return userMapper.toUserResponse(user);
    }
}
