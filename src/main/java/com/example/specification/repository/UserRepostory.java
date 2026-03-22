package com.example.specification.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.specification.model.usermodel;
public interface UserRepostory extends JpaRepository<usermodel, Long>, JpaSpecificationExecutor<usermodel> {

	@Override
	@EntityGraph(attributePaths = {"courses","p"})
	List<usermodel> findAll();

	@Override
	@EntityGraph(attributePaths = {"courses"})
	Optional<usermodel> findById(Long id);

}
