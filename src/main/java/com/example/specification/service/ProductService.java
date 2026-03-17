package com.example.specification.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.specification.dto.ProductDto;
import com.example.specification.model.Product;

public interface ProductService {

    void saveUserProduct(Product product, Long userId);

    Double totalPrice();

    List<Product> getByActiveOrId(Long id, Boolean active,
        String name, Double minPrice,
        Double maxPrice, Long userId, LocalDateTime dateTime,
        LocalDateTime start, LocalDateTime end, String... status);

    Double getTotalPriceBySpec(Long id, Boolean active,
        String name, Double minPrice,
        Double maxPrice, Long userId, LocalDateTime dateTime,
        LocalDateTime start, LocalDateTime end, String... status);

    /**
     * Combines Specification (dynamic filter → product list)
     * with JPQL (DB aggregate → user total price).
     * Returns both results in one DTO.
     */
    ProductDto searchWithSummary(Long userId, Long id, Boolean active,
        String name, Double minPrice, Double maxPrice,
        LocalDateTime dateTime, LocalDateTime start, LocalDateTime end,
        String... status);
}
