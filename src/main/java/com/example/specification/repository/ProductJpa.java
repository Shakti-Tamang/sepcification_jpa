package com.example.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.specification.model.Product;

@Repository
public interface ProductJpa extends JpaRepository<Product,Long >, JpaSpecificationExecutor<Product>{

    
}
