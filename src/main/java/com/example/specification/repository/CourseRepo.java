package com.example.specification.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.specification.model.Course;


@Repository
public interface CourseRepo extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

	@Override
	@EntityGraph(attributePaths = {"users"})
	List<Course> findAll();

	@Override
	@EntityGraph(attributePaths = {"users"})
	Optional<Course> findById(Long id);

}
