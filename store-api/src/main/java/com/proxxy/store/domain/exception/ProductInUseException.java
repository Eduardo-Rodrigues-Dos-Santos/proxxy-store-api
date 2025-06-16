package com.proxxy.store.domain.exception;

import java.util.UUID;

public class ProductInUseException extends RuntimeException {

    public ProductInUseException(String message) {
        super(message);
    }

    public ProductInUseException(UUID id){
        this(String.format("The Product with ID: %s is in use!", id));
    }
}
