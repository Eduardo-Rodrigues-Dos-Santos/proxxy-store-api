package com.proxxy.store.domain.model;

import com.proxxy.store.domain.exception.InventoryOperationException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "tb_product",
        indexes = @Index(name = "idx_product_name_description", columnList = "name, description"))
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;
    private String description;
    private BigDecimal value;

    @Column(name = "available_quantity")
    private Integer availableQuantity;

    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @BatchSize(size = 20)
    private Set<Category> categories = new HashSet<>();

    @Column(name = "image_link")
    private String imageLink;

    public void attachCategories(List<Category> categories) {
        this.categories.addAll(categories);
    }

    public void detachCategories(List<Category> categories) {
        this.categories.removeAll(categories);
    }

    public void updatePrice(BigDecimal value) {
        this.value = value;
    }

    public void increaseStock(Integer quantity) {
        this.availableQuantity += quantity;
    }

    public void decreaseStock(Integer quantity) {

        if (quantity > this.availableQuantity) {
            throw new InventoryOperationException(this.id);
        }
        this.availableQuantity -= quantity;
    }
}