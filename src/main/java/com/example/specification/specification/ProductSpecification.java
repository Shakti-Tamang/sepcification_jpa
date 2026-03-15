package com.example.specification.specification;

import javax.swing.Spring;

import org.hibernate.annotations.processing.SQL;
import org.hibernate.metamodel.mapping.ForeignKeyDescriptor.Side;
import org.springframework.data.jpa.domain.Specification;

import com.example.specification.model.Product;

public class ProductSpecification {

    private ProductSpecification() {
    
    }


//     Specification
//      ↓
// root → table reference
//      ↓
// criteriaBuilder → build condition
//      ↓
// query → combine into full SQL
//      ↓
// Spring Data JPA
//      ↓
// SQL executed in database
 

//     The three parameters:
// root — the table
// javaroot.get("id")  
// // = the "id" column in your table
// // like saying: users.id
// query — the full SELECT statement
// java// Represents the whole query structure
// // SELECT * FROM users
// // You rarely touch this directly for simple filters
// criteriaBuilder — the condition builder
// javacriteriaBuilder.equal(root.get("id"), id)
// // = WHERE id = 123
// // criteriaBuilder builds conditions like equal, like, greaterThan etc

// Side by side:
// JavaSQL equivalentroottable (users)root.get("id")column (users.id)criteriaBuilder.equal()= operatorqueryfull SELECT statement
    public static Specification<Product>  productById(Long id){

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Product> productByBoolean(Boolean isActive) {
       return (root,query,criteriaBilder)->criteriaBilder.equal(root.get("active"),isActive);
    }


    public static Specification<Product> searchByName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }


    // beween specification
    // WHERE price BETWEEN 100 AND 500
    public static Specification<Product> priceBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    } 
    
    // IN Operator
    public static Specification<Product> statusIn(String... statuses) {
        return (root, query, criteriaBuilder) -> root.get("status").in((Object[]) statuses);
    }

}
