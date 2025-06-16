package com.proxxy.store.api.v1.model.input;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductInputModel {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 600)
    private String description;

    @NotNull
    @Positive
    private BigDecimal value;

    @NotNull
    @PositiveOrZero
    private Integer availableQuantity;
}
