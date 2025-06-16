package com.proxxy.store.domain.exception;

import java.util.UUID;

public class InventoryOperationException extends RuntimeException {

    public InventoryOperationException(String message) {
        super(message);
    }

    public InventoryOperationException(UUID id) {
        this(String.format("The number of units to be removed from the product with ID: %s " +
                "cannot be greater than the amount available in stock.", id));
    }
}
