package com.example.specification.specification;

import javax.swing.Spring;

import org.hibernate.annotations.processing.SQL;
import org.hibernate.metamodel.mapping.ForeignKeyDescriptor.Side;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

import com.example.specification.model.Product;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
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
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
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

    public static Specification<Product> userIdEquals(Long userId) {
        return (root, query, criteriaBuilder) -> {
            if (userId == null) {
                return criteriaBuilder.conjunction();
            }

            Join<Object, Object> userJoin = root.join("usermodel", JoinType.INNER);
            return criteriaBuilder.equal(userJoin.get("id"), userId);
        };
    }

    public static Specification<Product> createAfter(LocalDateTime dateTime) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("createdAt"), dateTime);
    }

    // product added from to
    public static Specification<Product> createdBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("createdAt"), start, end);
    }




}
