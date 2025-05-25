package com.nmhoang.identity_service.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User existed"),
    USER_NOT_EXISTED(1002, "User not existed"),
    USERNAME_NOT_VALID(1003, "The username must be at least 4 characters"),
    PASSWORD_NOT_VALID(1004, "The password must be at least 8 characters"),
    INCORRECT_PASSWORD(1005, "Incorrect password"),
    INTERNAL_ERROR(1006, "Internal error"),
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
