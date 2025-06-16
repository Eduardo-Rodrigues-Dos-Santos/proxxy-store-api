package com.proxxy.store.domain.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductFilter {

    private String name;
    private List<String> categories;
}
