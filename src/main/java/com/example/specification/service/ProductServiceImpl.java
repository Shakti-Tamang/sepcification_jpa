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
    ,String name,Double minPrice ,
       
    Double maxPrice   ,  Long userId ,LocalDateTime dateTime  ,LocalDateTime start, LocalDateTime end,String ... status
                                                                   
 )     {                                                              

        Specification<Product> spec = Specification
                .where(ProductSpecification.productById(id))
                .and(ProductSpecification.isActive(active))
                .and(ProductSpecification.nameContains(name))
                .and(ProductSpecification.priceBetween(minPrice, maxPrice))
                .and(ProductSpecification.statusIn(status))
                .and(ProductSpecification.userIdEquals(userId))
                .and(ProductSpecification.createAfter(dateTime))
                .and(ProductSpecification.createdBetween(start, end));

        return productRepo.findAll(spec);
    }


    // jpa with specification to calculate total price based on filters
    @Override
    public Double getTotalPriceBySpec(Long id, Boolean active,
            String name, Double minPrice,
            Double maxPrice, Long userId, LocalDateTime dateTime,
            LocalDateTime start, LocalDateTime end, String... status) {

        Specification<Product> spec = Specification
                .where(ProductSpecification.productById(id))
                .and(ProductSpecification.isActive(active))
                .and(ProductSpecification.nameContains(name))
                .and(ProductSpecification.priceBetween(minPrice, maxPrice))
                .and(ProductSpecification.statusIn(status))
                .and(ProductSpecification.userIdEquals(userId))
                .and(ProductSpecification.createAfter(dateTime))
                .and(ProductSpecification.createdBetween(start, end));

        return productRepo.findAll(spec).stream()
                .filter(p -> p.getPrice() != null)
                .mapToDouble(p -> p.getPrice())
                .sum();
    }

    @Override
    public Double totalPrice() {
        return productRepo.totalPrice();
    }

}
