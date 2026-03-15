package com.example.specification.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.example.specification.model.Product;
import com.example.specification.repository.ProductJpa;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util. ArrayList;
import org.springframework.data.jpa.domain.Specification;

import com.example.specification.specification.ProductSpecification;
import com.example.specification.repository.UserRepostory;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductJpa productRepo;
        private final UserRepostory userRepostory;

    @Override
    public void saveUserProduct(Product product, Long userId) {
        var user = userRepostory.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));


        product.setUsermodel(user);

        if(user.getProducts() == null) {
            user.setProducts(new ArrayList<>());
        }

        user.getProducts().add(product);

    
     
        productRepo.save(product);
    }

    @Override
   public List<Product> getByActiveOrId(Long id, Boolean active
    ,String name,double minPrice ,
       
    double maxPrice   ,  Long userId ,LocalDateTime dateTime  ,LocalDateTime start, LocalDateTime end,String ... status
                                                                   
 )     {                                                              

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
        if (status != null && status.length > 0) {
            spec = spec.and(ProductSpecification.statusIn(status));
        }
        if (userId != null) {
            spec = spec.and(ProductSpecification.userIdEquals(userId));
        }
        if (dateTime != null) {
            spec = spec.and(ProductSpecification.createAfter(dateTime));
        }
        if (spec == null) {
            return productRepo.findAll();
        }   

        return productRepo.findAll(spec);
    }

}
