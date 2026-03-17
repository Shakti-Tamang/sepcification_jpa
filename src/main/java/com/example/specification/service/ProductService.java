package com.example.specification.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.specification.model.Product;

public interface ProductService {

    public void saveUserProduct(Product  product, Long userId);

    public Double totalPrice();

 public List<Product> getByActiveOrId(Long id, Boolean active
    ,String name,Double minPrice ,
      
    Double maxPrice   ,  Long userId   ,LocalDateTime dateTime,LocalDateTime start, LocalDateTime end,String ... status
                                                                   
 );  

    public Double getTotalPriceBySpec(Long id, Boolean active,
        String name, Double minPrice,
        Double maxPrice, Long userId, LocalDateTime dateTime,
        LocalDateTime start, LocalDateTime end, String... status);

}
