package com.proxxy.store.domain.exception;

public class CategoryNotFoundException extends EntityNotFoundException {

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(Long id) {
        this(String.format("There is no category with ID: %d!", id));
    }
}
