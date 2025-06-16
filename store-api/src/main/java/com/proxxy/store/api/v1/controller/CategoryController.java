package com.proxxy.store.api.v1.controller;

import com.proxxy.store.api.v1.mapper.CategoryMapper;
import com.proxxy.store.api.v1.model.input.CategoryInputModel;
import com.proxxy.store.api.v1.model.output.CategoryModel;
import com.proxxy.store.api.v1.model.output.PageResponse;
import com.proxxy.store.core.security.annotations.CheckSecurity;
import com.proxxy.store.domain.model.Category;
import com.proxxy.store.domain.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;


    @CheckSecurity.Category.Consult
    @GetMapping
    public ResponseEntity<PageResponse<CategoryModel>> findAll(@PageableDefault Pageable pageable) {

        Page<CategoryModel> categoryModels = categoryService.findAll(pageable)
                .map(categoryMapper::toCategoryModel);
        return ResponseEntity.ok(PageResponse.of(categoryModels));
    }

    @CheckSecurity.Category.Edit
    @PostMapping
    public ResponseEntity<CategoryModel> addCategory(@RequestBody @Valid CategoryInputModel inputModel) {

        Category newCategory = categoryMapper.toCategory(inputModel);
        Category category = categoryService.addCategory(newCategory);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryMapper.toCategoryModel(category));
    }

    @CheckSecurity.Category.Edit
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable Long categoryId,
                                                        @RequestBody @Valid CategoryInputModel inputModel) {

        Category currentCategory = categoryService.findById(categoryId);
        categoryMapper.copyToDomainObject(inputModel, currentCategory);

        Category category = categoryService.updateCategory(currentCategory);
        return ResponseEntity.ok(categoryMapper.toCategoryModel(category));
    }

    @CheckSecurity.Category.Edit
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
