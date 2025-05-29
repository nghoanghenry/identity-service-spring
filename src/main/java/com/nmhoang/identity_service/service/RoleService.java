package com.nmhoang.identity_service.service;

import com.nmhoang.identity_service.dto.request.RoleRequest;
import com.nmhoang.identity_service.dto.response.RoleResponse;
import com.nmhoang.identity_service.entity.Role;
import com.nmhoang.identity_service.mapper.RoleMapper;
import com.nmhoang.identity_service.repository.PermissionRepository;
import com.nmhoang.identity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository RoleRepository;
    PermissionRepository PermissionRepository;
    RoleMapper RoleMapper;

    public RoleResponse create(RoleRequest RoleRequest) {
        Role role = RoleMapper.toRole(RoleRequest);
        var permissions = PermissionRepository.findAllById(RoleRequest.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        RoleRepository.save(role);
        return RoleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> findAll() {
        List<Role> Roles = RoleRepository.findAll();
        return Roles.stream()
                .map(RoleMapper::toRoleResponse)
                .toList();
    }

    public void delete(String Role) {
        RoleRepository.deleteById(Role);
    }
}
