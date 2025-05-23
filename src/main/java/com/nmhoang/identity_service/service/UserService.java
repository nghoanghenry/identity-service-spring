package com.nmhoang.identity_service.service;

import com.nmhoang.identity_service.dto.request.UserCreationRequest;
import com.nmhoang.identity_service.dto.request.UserUpdateRequest;
import com.nmhoang.identity_service.entity.User;
import com.nmhoang.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreationRequest userCreationRequest) {
        User user = new User();
        user.setUsername(userCreationRequest.getUsername());
        user.setPassword(userCreationRequest.getPassword());
        user.setFirstName(userCreationRequest.getFirstName());
        user.setLastName(userCreationRequest.getLastName());
        user.setDob(userCreationRequest.getDob());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        );
    }

    public User updateUser(String id, UserUpdateRequest userUpdateRequest) {
        User user = getUserById(id);

        user.setPassword(userUpdateRequest.getPassword());
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setDob(userUpdateRequest.getDob());

        return userRepository.save(user);

    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
