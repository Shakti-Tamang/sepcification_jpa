package com.example.specification.service;

import com.example.specification.model.Product;

import java.util.List;
import java.time.LocalDateTime;

public interface ProductService {

    public void saveUserProduct(Product  product, Long userId);

 public List<Product> getByActiveOrId(Long id, Boolean active
    ,String name,double minPrice ,
      
    double maxPrice   ,  Long userId   ,LocalDateTime dateTime,String ... status
                                                                   
 );  

}
