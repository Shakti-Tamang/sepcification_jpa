package com.example.specification.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.specification.model.usermodel;
public interface UserRepostory extends JpaRepository<usermodel, Long>, JpaSpecificationExecutor<usermodel> {

}
