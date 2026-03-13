package com.example.specification.service;

import org.springframework.stereotype.Service;

import com.example.specification.model.Product;
import com.example.specification.repository.ProductJpa;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

import com.example.specification.specification.ProductSpecification;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductJpa productRepo;

    @Override
    public void saveUserProduct(Product product) {

        productRepo.save(product);
    }

    @Override
    public List<Product> getByActiveOrId(Long id, Boolean active,String name,double minPrice ,
    double maxPrice   )          {                                                              

        Specification<Product> spec = Specification.where(null);

        if (id != null) {
            spec = spec.and(ProductSpecification.productById(id));
        }

        if (active != null) {
            spec = spec.and(ProductSpecification.productByBoolean(active));
        }
        if (name != null) {
            spec = spec.and(ProductSpecification.searchByName(name));
        }
        if (minPrice != 0 || maxPrice != 0) {
            spec = spec.and(ProductSpecification.priceBetween(minPrice, maxPrice));
        }
        if (spec == null) {
            return productRepo.findAll();
        }   

        return productRepo.findAll(spec);
    }

}
