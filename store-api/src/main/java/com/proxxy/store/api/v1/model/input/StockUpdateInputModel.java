package com.proxxy.store.api.v1.model.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockUpdateInputModel {

    @NotNull
    @Positive
    private Integer quantity;
}
