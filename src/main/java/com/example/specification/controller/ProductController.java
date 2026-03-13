package com.example.specification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
// http://localhost:8095/getByActiveOrId?name=App&id=7&active=true
    @GetMapping("/getByActiveOrId")
    public ResponseEntity<?> getByActiveOrId(@RequestParam(value = "id", required = false) Long id,
                                             @RequestParam(value = "active", required = false) Boolean active,
                                            
                                            @RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "minPrice", required = false, defaultValue = "0") double minPrice,    
                                            @RequestParam(value = "maxPrice", required = false, defaultValue = "0") double maxPrice
                                             ) {

        return ResponseEntity.ok(productService.getByActiveOrId(id, active,name,minPrice,maxPrice));
    }


}
