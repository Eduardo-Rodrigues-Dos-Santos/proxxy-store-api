package com.proxxy.store.api.v1.model.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PriceInputModel {

    @NotNull
    @Positive
    private BigDecimal value;
}
