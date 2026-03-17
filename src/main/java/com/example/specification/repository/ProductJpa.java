package com.example.specification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.specification.model.Product;

@Repository
public interface ProductJpa extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    // JPQL: global total price (no filter)
    @Query("SELECT SUM(p.price) FROM Product p")
    Double totalPrice();

    // JPQL: total price for a specific user
    @Query("SELECT SUM(p.price) FROM Product p WHERE p.usermodel.id = :userId")
    Double totalPriceByUserId(@Param("userId") Long userId);

    // JPQL: find products by status and optional price range
    @Query("SELECT p FROM Product p WHERE " +
           "(:status IS NULL OR p.status = :status) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Product> findByStatusAndPriceRange(
            @Param("status") String status,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice);

    // JPQL: search products by name keyword (LIKE) for a given user
    @Query("SELECT p FROM Product p WHERE p.usermodel.id = :userId AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchByUserAndKeyword(
            @Param("userId") Long userId,
            @Param("keyword") String keyword);
}
