package com.nmhoang.identity_service.service;

import com.nmhoang.identity_service.dto.request.PermissionRequest;
import com.nmhoang.identity_service.dto.response.PermissionResponse;
import com.nmhoang.identity_service.entity.Permission;
import com.nmhoang.identity_service.mapper.PermissionMapper;
import com.nmhoang.identity_service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest permissionRequest) {
        Permission permission = permissionMapper.toPermission(permissionRequest);
        permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> findAll() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
