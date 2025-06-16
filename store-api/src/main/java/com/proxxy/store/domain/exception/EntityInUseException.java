package com.proxxy.store.domain.exception;

public abstract class EntityInUseException extends RuntimeException {

    protected EntityInUseException(String message) {
        super(message);
    }
}
