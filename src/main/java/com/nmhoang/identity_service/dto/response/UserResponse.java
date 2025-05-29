package com.nmhoang.identity_service.dto.response;

import com.nmhoang.identity_service.entity.Role;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
    Set<Role> roles;
}
