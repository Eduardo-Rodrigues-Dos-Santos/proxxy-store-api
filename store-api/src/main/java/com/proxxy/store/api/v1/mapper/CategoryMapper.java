package com.proxxy.store.api.v1.mapper;

import com.proxxy.store.api.v1.model.input.CategoryInputModel;
import com.proxxy.store.api.v1.model.output.CategoryModel;
import com.proxxy.store.domain.model.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryMapper {

    private ModelMapper modelMapper;

    public Category toCategory(CategoryInputModel inputModel) {
        return modelMapper.map(inputModel, Category.class);
    }

    public CategoryModel toCategoryModel(Category category) {
        return modelMapper.map(category, CategoryModel.class);
    }

    public void copyToDomainObject(CategoryInputModel source, Category target) {
        modelMapper.map(source, target);
    }
}
