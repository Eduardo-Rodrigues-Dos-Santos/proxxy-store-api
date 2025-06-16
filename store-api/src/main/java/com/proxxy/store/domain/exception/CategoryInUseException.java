package com.proxxy.store.domain.exception;

public class CategoryInUseException extends EntityInUseException {

    public CategoryInUseException(String message) {
        super(message);
    }

    public CategoryInUseException(Long id) {
        this(String.format("The Category with ID: %d is in use!", id));
    }
}
