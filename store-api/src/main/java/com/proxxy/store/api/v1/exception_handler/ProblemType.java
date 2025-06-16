package com.proxxy.store.api.v1.exception_handler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
    INVALID_OPERATION("/invalid-operation", "Invalid operation"),
    INCOMPREHENSIBLE_MESSAGE("/incomprehensible-message", "Incomprehensible message"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    SYSTEM_ERROR("/system-error", "System error"),
    INVALID_DATA("/invalid-data", "Invalid data"),
    FORBIDDEN("/access-denied", "Access denied");
    private final String title;
    private final String uri;

    ProblemType(String path, String title) {
        this.uri = "https://proxxy.com.br" + path;
        this.title = title;
    }
}