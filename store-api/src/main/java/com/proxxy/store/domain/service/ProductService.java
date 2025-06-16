package com.proxxy.store.domain.service;

import com.proxxy.store.domain.exception.NameInUseException;
import com.proxxy.store.domain.exception.ProductInUseException;
import com.proxxy.store.domain.exception.ProductNotFoundException;
import com.proxxy.store.domain.filter.ProductFilter;
import com.proxxy.store.domain.model.Category;
import com.proxxy.store.domain.model.Product;
import com.proxxy.store.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.proxxy.store.infrastructure.repository.specifications.ProductSpecsFactory.productsLikeNameAndCategories;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryService categoryService;


    public Product findById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {

        Page<Product> page = productRepository.findAll(pageable);
        page.getContent().forEach(product -> Hibernate.initialize(product.getCategories()));
        return page;
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(ProductFilter productFilter, Pageable pageable) {

        Page<Product> page = productRepository.findAll(productsLikeNameAndCategories(productFilter), pageable);
        page.getContent().forEach(product -> Hibernate.initialize(product.getCategories()));
        return page;
    }

    @Transactional
    public Product addProduct(Product product) {

        Optional<Product> optionalProduct = productRepository.findByName(product.getName());

        if (optionalProduct.isPresent()) {
            throw new NameInUseException(product.getName());
        }
        return productRepository.save(product);
    }

    @Transactional
    public void attachCategory(UUID productId, List<Long> categoryIds) {

        Product product = findById(productId);

        List<Category> categories = categoryService.findAllById(categoryIds);
        product.attachCategories(categories);
    }

    @Transactional
    public void detachCategory(UUID productId, List<Long> categoryIds) {

        Product product = findById(productId);
        List<Category> categories = categoryService.findAllById(categoryIds);
        product.detachCategories(categories);
    }

    @Transactional
    public void increaseStock(UUID productId, Integer quantity) {

        Product product = findById(productId);
        product.increaseStock(quantity);
    }

    @Transactional
    public void decreaseStock(UUID productId, Integer quantity) {

        Product product = findById(productId);
        product.decreaseStock(quantity);
    }

    @Transactional
    public void updatePrice(UUID productId, BigDecimal value) {

        Product product = findById(productId);
        product.updatePrice(value);
    }

    @Transactional
    public Product updateProduct(Product product) {

        Optional<Product> optionalProduct = productRepository.findByName(product.getName());

        if (optionalProduct.isPresent() && !product.equals(optionalProduct.get())) {
            throw new NameInUseException(product.getName());
        }
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(UUID productId) {

        try {
            Product product = findById(productId);
            productRepository.delete(product);
            productRepository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new ProductInUseException(productId);
        }
    }
}