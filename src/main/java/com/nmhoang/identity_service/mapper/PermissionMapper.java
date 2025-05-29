package com.nmhoang.identity_service.mapper;

import com.nmhoang.identity_service.dto.request.PermissionRequest;
import com.nmhoang.identity_service.dto.request.UserCreationRequest;
import com.nmhoang.identity_service.dto.request.UserUpdateRequest;
import com.nmhoang.identity_service.dto.response.PermissionResponse;
import com.nmhoang.identity_service.dto.response.UserResponse;
import com.nmhoang.identity_service.entity.Permission;
import com.nmhoang.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);
    PermissionResponse toPermissionResponse(Permission permission);
    void updatePermission(@MappingTarget Permission permission, PermissionRequest permissionRequest);
}
