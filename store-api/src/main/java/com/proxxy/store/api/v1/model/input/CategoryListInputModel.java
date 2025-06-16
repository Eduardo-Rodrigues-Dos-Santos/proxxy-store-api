package com.proxxy.store.api.v1.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryListInputModel {

    @Valid
    @NotNull
    @Size(min = 1, max = 5)
    private List<@NotNull @Positive Long> categoryIds;
}