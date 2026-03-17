package com.example.specification.dto;

import java.util.List;

import com.example.specification.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {

    // --- from Specification (dynamic filters) ---
    private List<Product> filteredProducts;  // only products matching the filters
    private int filteredCount;               // how many matched
    private Double filteredTotal;            // sum of matched product prices (in-memory stream)

    // --- from JPQL (DB aggregate) ---
    private Double userTotalPrice;           // SUM of ALL products of this user from DB (JPQL)
}
