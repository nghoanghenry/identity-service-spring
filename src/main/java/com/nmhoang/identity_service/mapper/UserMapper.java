package com.nmhoang.identity_service.mapper;

import com.nmhoang.identity_service.dto.request.UserCreationRequest;
import com.nmhoang.identity_service.dto.request.UserUpdateRequest;
import com.nmhoang.identity_service.dto.response.UserResponse;
import com.nmhoang.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
