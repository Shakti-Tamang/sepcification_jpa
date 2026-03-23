package com.example.specification.specification;

import java.time.LocalDateTime;

import com.example.specification.model.Course;
import org.springframework.data.jpa.domain.Specification;

import com.example.specification.enums.ProductStatus;
import com.example.specification.model.Product;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class ProductSpecification {

    private ProductSpecification() {
    
    }


    // For a NestJS project, FileZilla is commonly used with Hostinger or VPS when you don't have CI/CD set up — you just build locally and upload the files manually.

    // ❌ Complex joins across multiple tables
// ❌ GROUP BY / aggregate functions (SUM, COUNT, AVG)
// ❌ Subqueries easily
// ❌ Custom projections / selecting specific columns
// ❌ Native SQL features


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
    public static Specification<Product> productById(Long id){
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    public static Specification<Product> productByBoolean(Boolean isActive) {
       return (root,query,criteriaBuilder) -> {
           if (isActive == null) {
               return criteriaBuilder.conjunction();
           }
           return criteriaBuilder.equal(root.get("active"), isActive);
       };
    }


    public static Specification<Product> searchByName(String name) {
        return nameContains(name);
    }

    public static Specification<Product> nameContains(String name) {

        // name coming from db is also changed to lower case and 
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
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) {
                return criteriaBuilder.conjunction();
            }
            if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
            }
            if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    public static Specification<Product> priceGreaterThanOrEqual(Double minPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLessThanOrEqual(Double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
    
    // IN Operator
    public static Specification<Product> statusIn(String... statuses) {
        return (root, query, criteriaBuilder) -> {
            if (statuses == null || statuses.length == 0) {
                return criteriaBuilder.conjunction();
            }
            return root.get("status").in((Object[]) statuses);
        };
    }

    public static Specification<Product> hasStatus(ProductStatus status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<Product> isActive(Boolean active) {
        return productByBoolean(active);
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

    public static Specification<Course>  CourseForUser(Long userId){

        return (root,query,criteriaBuilder)->{

            if(userId==null){

                return criteriaBuilder.conjunction();
            }

            Join<Object,Object>userJoin=root.join("users",JoinType.INNER);

            return criteriaBuilder.equal(userJoin.get("id"),userId);
        };


    }

    public static Specification<Product> createAfter(LocalDateTime dateTime) {
        return (root, query, criteriaBuilder) -> {
            if (dateTime == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("createdAt"), dateTime);
        };
    }

    // product added from to
    public static Specification<Product> createdBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, criteriaBuilder) -> {
            if (start == null && end == null) {
                return criteriaBuilder.conjunction();
            }
            if (start != null && end != null) {
                return criteriaBuilder.between(root.get("createdAt"), start, end);
            }
            if (start != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), start);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), end);
        };
    }

    // The SQL AND operator is a logical operator used in the WHERE or HAVING clause to filter records based on multiple conditions, all of which must evaluate to TRUE for a row to be included in the result set

    




}
