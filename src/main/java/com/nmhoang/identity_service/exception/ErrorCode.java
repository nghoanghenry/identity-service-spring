package com.nmhoang.identity_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_EXISTED(1001, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1002, "User not existed", HttpStatus.NOT_FOUND),
    USERNAME_NOT_VALID(1003, "The username must be at least 4 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_VALID(1004, "The password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    INCORRECT_PASSWORD(1005, "Incorrect password", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(1006, "Internal error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATION(1007, "Unauthorized", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008, "Unauthorized client", HttpStatus.UNAUTHORIZED),
    DOB_INVALID(1009, "Age must be over {min}", HttpStatus.BAD_REQUEST),
    ;
    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHttpStatusCode(HttpStatusCode httpStatusCode) {}
}
