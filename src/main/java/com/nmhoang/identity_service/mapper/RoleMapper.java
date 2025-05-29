package com.nmhoang.identity_service.mapper;

import com.nmhoang.identity_service.dto.request.RoleRequest;
import com.nmhoang.identity_service.dto.response.RoleResponse;
import com.nmhoang.identity_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target ="permissions", ignore = true)
    Role toRole(RoleRequest RoleRequest);
    RoleResponse toRoleResponse(Role Role);
}
