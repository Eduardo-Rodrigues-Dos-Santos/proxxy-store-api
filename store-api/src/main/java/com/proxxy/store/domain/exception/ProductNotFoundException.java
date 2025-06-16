package com.proxxy.store.domain.exception;

import java.util.UUID;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(UUID productId) {
        this(String.format("There is no product with ID: %s", productId.toString()));
    }
}
