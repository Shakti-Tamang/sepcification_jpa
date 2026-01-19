package com.example.specification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.specification.model.Product;
import com.example.specification.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping("/saveUserProduct")
    public ResponseEntity<?> saveProduct(@RequestBody Product entity) {
        productService.saveUserProduct(entity);

        return ResponseEntity.ok("success");
    }

}
