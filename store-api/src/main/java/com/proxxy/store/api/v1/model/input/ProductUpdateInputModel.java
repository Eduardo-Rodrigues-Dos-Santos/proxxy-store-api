package com.proxxy.store.api.v1.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateInputModel {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 600)
    private String description;
}
