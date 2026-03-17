package com.example.specification.service;

import javax.swing.Spring;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.specification.model.Product;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.With;

public class Sortihng {
// With sort:
// java// Always returns in a predictable order
// SELECT * FROM products ORDER BY name ASC

// How to use in Spring Boot:
// Option 1 — In Repository:
// java// Sort by name ascending
// List<Product> findAll(Sort sort);

// // Usage
// productRepository.findAll(Sort.by("name").ascending());
// productRepository.findAll(Sort.by("price").descending());

// // Multiple fields
// productRepository.findAll(
//     Sort.by("name").ascending()
//         .and(Sort.by("price").descending())
// );
// Option 2 — With Pageable:
// java// In controller
// @GetMapping
// public Page<Product> getAll(
//     @RequestParam(defaultValue = "0") int page,
//     @RequestParam(defaultValue = "10") int size,
//     @RequestParam(defaultValue = "name") String sortBy,
//     @RequestParam(defaultValue = "asc") String direction
// ) {
//     Sort sort = direction.equals("asc")
//         ? Sort.by(sortBy).ascending()
//         : Sort.by(sortBy).descending();

//     Pageable pageable = PageRequest.of(page, size, sort);
//     return productRepository.findAll(pageable);
// }
// Option 3 — In application.properties:
// properties# Default sort for Spring Data REST
// spring.data.rest.default-sort=name,asc

}
