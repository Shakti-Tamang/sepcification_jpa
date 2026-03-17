package com.example.specification.service;

import java.util.List;

import com.example.specification.model.Course;

public interface CourseService {

    public void saveCourse(Course course,Long userId);

    public List<Course> getAllCourses();

    public Course getCourseById(Long id);

}
