package com.nmhoang.identity_service.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nmhoang.identity_service.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_NOT_VALID")
    String username;

    @Size(min = 8, message = "PASSWORD_NOT_VALID")
    String password;
    String firstName;
    String lastName;

    @DobConstraint(min = 10, message = "DOB_INVALID")
    LocalDate dob;
}
