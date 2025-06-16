package com.proxxy.store.domain.service;

import com.proxxy.store.domain.exception.CategoryInUseException;
import com.proxxy.store.domain.exception.CategoryNotFoundException;
import com.proxxy.store.domain.exception.NameInUseException;
import com.proxxy.store.domain.model.Category;
import com.proxxy.store.domain.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;


    public List<Category> findAllById(List<Long> categoryIds) {
        return categoryRepository.findAllById(categoryIds);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Transactional
    public Category addCategory(Category category) {

        Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());

        if (optionalCategory.isPresent()) {
            throw new NameInUseException(category.getName());
        }
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(Category category) {

        Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());

        if (optionalCategory.isPresent() && !category.equals(optionalCategory.get())) {
            throw new NameInUseException(category.getName());
        }
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {

        try {
            Category category = findById(categoryId);
            categoryRepository.delete(category);
            categoryRepository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new CategoryInUseException(categoryId);
        }
    }
}
