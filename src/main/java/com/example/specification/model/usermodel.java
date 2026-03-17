package com.example.specification.model;

import lombok.*;
import jakarta.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    private String email;

    @Column(length = 1000)
    private String password;

    @OneToMany(mappedBy = "usermodel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = jakarta.persistence.FetchType.LAZY)
    @JsonManagedReference("usermodel-products")
    private List<Product> products;

    @ManyToMany(mappedBy = "users", cascade ={CascadeType.PERSIST, CascadeType.MERGE})
     @JsonIgnoreProperties("users") 
    private List<Course> courses;

}
