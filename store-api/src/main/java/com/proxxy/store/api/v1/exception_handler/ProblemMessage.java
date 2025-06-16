package com.proxxy.store.api.v1.exception_handler;
import lombok.Getter;

@Getter
public enum ProblemMessage {

    INVALID_BODY("The request body is invalid, check syntax error."),
    INVALID_TYPE("The %s field was assigned an invalid type, please enter a value compatible with %s."),
    INVALID_URL_PARAMETER("The URL parameter %s received the value '%s' which is invalid, please enter a value compatible with UUID."),
    NON_EXISTENT_RESOURCE("Resource %s non-existent."),
    SYSTEM_ERROR("An unexpected internal error has occurred. Try again and if the problem persists, contact your system administrator."),
    INVALID_DATA("One or more fields are invalid, fill them in correctly and try again."),
    FORBIDDEN("You do not have permission to perform this operation.");
    private String message;

    ProblemMessage(String message) {
        this.message = message;
    }
}
