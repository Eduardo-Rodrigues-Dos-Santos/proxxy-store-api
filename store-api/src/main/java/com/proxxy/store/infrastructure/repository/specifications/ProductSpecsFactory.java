package com.proxxy.store.infrastructure.repository.specifications;

import com.proxxy.store.domain.filter.ProductFilter;
import com.proxxy.store.domain.model.Product;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductSpecsFactory {

    private ProductSpecsFactory() {
    }

    public static Specification<Product> productsLikeNameAndCategories(ProductFilter productFilter) {

        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {

            ArrayList<Predicate> predicates = filterPredicates(productFilter, root, builder);
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static ArrayList<Predicate> filterPredicates(ProductFilter productFilter, Root<Product> root,
                                                         CriteriaBuilder builder) {

        ArrayList<Predicate> predicates = new ArrayList<>();
        List<String> categories = productFilter.getCategories();
        String productName = productFilter.getName();

        if (productName != null) {
            predicates.add(builder.like(root.get("name"), "%" + productName + "%"));
        }

        if (categories != null && !categories.isEmpty()) {

            var pre = new ArrayList<Predicate>();
            Path<Object> categoryName = root.get("categories").get("name");

            categories.forEach(name -> pre.add(builder.equal(categoryName, name)));
            Optional<Predicate> optionalPredicate = pre.stream().reduce(builder::or);
            optionalPredicate.ifPresent(predicates::add);
        }

        return predicates;
    }
}
