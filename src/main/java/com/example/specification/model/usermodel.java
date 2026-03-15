package com.example.specification.model;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;
import jakarta.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.example.specification.enums.ProductStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class usermodel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    private Double price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private Boolean active;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "usermodel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("usermodel-products")
    private List<Product> products;

}
