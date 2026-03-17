package com.example.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.specification.model.Course;


@Repository
public interface CourseRepo extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

}
