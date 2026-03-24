package com.example.specification.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.specification.model.Product;
import com.example.specification.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;
    @PostMapping("/saveUserProduct/{id}")
    public ResponseEntity<?> saveProduct(@RequestBody Product entity, @PathVariable("id") Long id) {
        productService.saveUserProduct(entity, id);
        return ResponseEntity.ok("success");
    }

    // http://localhost:8095/getByActiveOrId?name=App&id=7&active=true
    @GetMapping("/getByActiveOrId")
    public ResponseEntity<?> getByActiveOrId(@RequestParam(value = "id", required = false) Long id,
                                             @RequestParam(value = "active", required = false) Boolean active,
                                             @RequestParam(value = "search", required = false) String name,
                                             @RequestParam(value = "minPrice", required = false) Double minPrice,
                                             @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                             @RequestParam(value = "status", required = false) String[] status,
                                             @RequestParam(value = "userId", required = false) Long userId,
                                             @RequestParam(value = "dateTime", required = false) LocalDateTime dateTime,
                                             @RequestParam(value = "start", required = false) LocalDateTime start,
                                             @RequestParam(value = "end", required = false) LocalDateTime end,
                                             @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                             @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(productService.getByActiveOrId(id, active, name, minPrice, maxPrice, userId, dateTime, start, end, sortBy, sortDir, status));
    }

    /**
     * Specification + JPQL combined in one call.
     *
     * filteredProducts  → Specification (dynamic filters, any param optional)
     * filteredTotal     → sum of matched products (in-memory stream)
     * userTotalPrice    → JPQL DB aggregate (full user portfolio, unaffected by filters)
     *
     * Example:
     * GET http://localhost:8095/search?userId=3&search=phone&active=true&minPrice=100
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchWithSummary(
            @RequestParam(value = "userId",    required = false) Long userId,
            @RequestParam(value = "id",        required = false) Long id,
            @RequestParam(value = "active",    required = false) Boolean active,
            @RequestParam(value = "search",    required = false) String name,
            @RequestParam(value = "minPrice",  required = false) Double minPrice,
            @RequestParam(value = "maxPrice",  required = false) Double maxPrice,
            @RequestParam(value = "status",    required = false) String[] status,
            @RequestParam(value = "dateTime",  required = false) LocalDateTime dateTime,
            @RequestParam(value = "start",     required = false) LocalDateTime start,
            @RequestParam(value = "end",       required = false) LocalDateTime end,
            @RequestParam(value = "sortBy",    required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir",   required = false, defaultValue = "asc") String sortDir) {

        return ResponseEntity.ok(
                productService.searchWithSummary(userId, id, active, name,
                minPrice, maxPrice, dateTime, start, end, sortBy, sortDir, status));
    }
}

