package com.example.specification.service;

import org.springframework.stereotype.Service;

import com.example.specification.model.Product;
import com.example.specification.repository.ProductJpa;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductJpa productRepo;

    @Override
    public void saveUserProduct(Product product) {

        productRepo.save(product);
    }

}
