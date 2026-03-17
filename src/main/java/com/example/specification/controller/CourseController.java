package com.example.specification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.specification.model.Course;
import com.example.specification.service.CourseService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor

@RequestMapping("/courses")
public class CourseController {

    private final CourseService courtsService;

    @PostMapping("/savCourse")

    public ResponseEntity<?> saveCourse(@RequestBody Course course, @RequestParam("userId") Long userId) {
        courtsService.saveCourse(course, userId);
        return ResponseEntity.ok().build();
    }

}
