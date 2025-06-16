package com.proxxy.store.api.v1.model.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ProductModel {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal value;
    private Integer availableQuantity;
    private String imageLink;
    private Set<SimpleCategoryModel> categories;
}
