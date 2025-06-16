package com.proxxy.store.api.v1.mapper;

import com.proxxy.store.api.v1.model.input.ProductInputModel;
import com.proxxy.store.api.v1.model.input.ProductUpdateInputModel;
import com.proxxy.store.api.v1.model.output.ProductModel;
import com.proxxy.store.domain.model.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapper {

    private ModelMapper modelMapper;

    public Product toProduct(ProductInputModel productInputModel) {
        return modelMapper.map(productInputModel, Product.class);
    }

    public ProductModel toProductModel(Product product) {
        return modelMapper.map(product, ProductModel.class);
    }

    public void copyToDomainObject(ProductUpdateInputModel source, Product target) {
        modelMapper.map(source, target);
    }
}
