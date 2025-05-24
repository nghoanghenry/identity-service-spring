package com.nmhoang.identity_service.controller;

import com.nmhoang.identity_service.dto.request.UserCreationRequest;
import com.nmhoang.identity_service.dto.request.UserUpdateRequest;
import com.nmhoang.identity_service.dto.response.ApiResponse;
import com.nmhoang.identity_service.dto.response.UserResponse;
import com.nmhoang.identity_service.entity.User;
import com.nmhoang.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<User> postUser(@RequestBody @Valid UserCreationRequest userCreationRequest){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.createUser(userCreationRequest));
        return apiResponse;
    }

    @GetMapping
    List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest userUpdateRequest){
        return userService.updateUser(userId, userUpdateRequest);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User deleted";
    }
}
