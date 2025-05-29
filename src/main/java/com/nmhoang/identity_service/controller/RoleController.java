package com.nmhoang.identity_service.controller;

import com.nmhoang.identity_service.dto.request.RoleRequest;
import com.nmhoang.identity_service.dto.response.ApiResponse;
import com.nmhoang.identity_service.dto.response.RoleResponse;
import com.nmhoang.identity_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService RoleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest RoleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .data(RoleService.create(RoleRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .data(RoleService.findAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable("role") String Role) {
        RoleService.delete(Role);
        return ApiResponse.<Void>builder().build();
    }
}
