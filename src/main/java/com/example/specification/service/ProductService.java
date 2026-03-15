package com.example.specification.service;

import com.example.specification.model.Product;

import java.util.List;

public interface ProductService {

    public void saveUserProduct(Product  product);

 public List<Product> getByActiveOrId(Long id, Boolean active
    ,String name,double minPrice ,
    double maxPrice   ,String status                                                                      
 );  

}
