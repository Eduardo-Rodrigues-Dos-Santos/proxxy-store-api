package com.proxxy.store.api.v1.exception_handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidField {

    private String field;
    private String message;

    public InvalidField(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
