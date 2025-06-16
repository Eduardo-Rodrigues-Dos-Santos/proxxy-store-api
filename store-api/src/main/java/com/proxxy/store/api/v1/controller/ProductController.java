package com.proxxy.store.api.v1.controller;

import com.proxxy.store.api.v1.mapper.ProductMapper;
import com.proxxy.store.api.v1.model.input.*;
import com.proxxy.store.api.v1.model.output.PageResponse;
import com.proxxy.store.api.v1.model.output.ProductModel;
import com.proxxy.store.core.security.annotations.CheckSecurity;
import com.proxxy.store.domain.filter.ProductFilter;
import com.proxxy.store.domain.model.Product;
import com.proxxy.store.domain.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;


    @CheckSecurity.Product.Consult
    @GetMapping("/{productId}")
    public ResponseEntity<ProductModel> findById(@PathVariable UUID productId) {

        Product product = productService.findById(productId);
        return ResponseEntity.ok(productMapper.toProductModel(product));
    }

    @CheckSecurity.Product.Consult
    @GetMapping
    public ResponseEntity<PageResponse<ProductModel>> findAll(@PageableDefault Pageable pageable,
                                                              ProductFilter productFilter) {

        if (productFilter != null) {
            Page<ProductModel> productModels = productService.findAll(productFilter, pageable)
                    .map(productMapper::toProductModel);
            return ResponseEntity.ok(PageResponse.of(productModels));
        }

        Page<ProductModel> productModels = productService.findAll(pageable).map(productMapper::toProductModel);
        return ResponseEntity.ok(PageResponse.of(productModels));
    }

    @CheckSecurity.Product.Edit
    @PostMapping
    public ResponseEntity<ProductModel> addProduct(@RequestBody @Valid ProductInputModel inputModel) {

        Product newProduct = productMapper.toProduct(inputModel);
        Product product = productService.addProduct(newProduct);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(productMapper.toProductModel(product));
    }

    @CheckSecurity.Product.Edit
    @PutMapping("/{productId}/categories")
    public void attachCategory(@PathVariable UUID productId,
                               @RequestBody @Valid CategoryListInputModel categoryListInputModel) {

        List<Long> categoryIds = categoryListInputModel.getCategoryIds();
        productService.attachCategory(productId, categoryIds);
    }

    @CheckSecurity.Product.Edit
    @DeleteMapping("/{productId}/categories")
    public void detachCategory(@PathVariable UUID productId,
                               @RequestBody @Valid CategoryListInputModel categoryListInputModel) {

        List<Long> categoryIds = categoryListInputModel.getCategoryIds();
        productService.detachCategory(productId, categoryIds);
    }

    @CheckSecurity.Product.Edit
    @PutMapping("/{productId}/stock")
    public void increaseStock(@PathVariable UUID productId,
                              @RequestBody @Valid StockUpdateInputModel stock) {

        Integer quantity = stock.getQuantity();
        productService.increaseStock(productId, quantity);
    }

    @CheckSecurity.Product.Edit
    @DeleteMapping("/{productId}/stock")
    public void decreaseStock(@PathVariable UUID productId,
                              @RequestBody @Valid StockUpdateInputModel stockUpdate) {

        Integer quantity = stockUpdate.getQuantity();
        productService.decreaseStock(productId, quantity);
    }

    @CheckSecurity.Product.Edit
    @PutMapping("/{productId}/price")
    public void updatePrice(@PathVariable UUID productId, @RequestBody @Valid PriceInputModel priceInputModel) {

        BigDecimal value = priceInputModel.getValue();
        productService.updatePrice(productId, value);
    }

    @CheckSecurity.Product.Edit
    @PutMapping("/{productId}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable UUID productId,
                                                      @RequestBody @Valid ProductUpdateInputModel productUpdate) {

        Product currentProduct = productService.findById(productId);
        productMapper.copyToDomainObject(productUpdate, currentProduct);

        Product product = productService.updateProduct(currentProduct);
        return ResponseEntity.ok(productMapper.toProductModel(product));
    }

    @CheckSecurity.Product.Edit
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
    }
}
