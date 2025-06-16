package com.proxxy.store.domain.exception;

public class NameInUseException extends RuntimeException {
    public NameInUseException(String name) {
        super(String.format("The name: %s is already in use!", name));
    }
}
