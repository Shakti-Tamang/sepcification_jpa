package com.example.specification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.specification.model.Course;
import com.example.specification.service.CourseService;

import lombok.RequiredArgsConstructor;

import java.util.List;


@RestController
@RequiredArgsConstructor

@RequestMapping("/courses")
public class CourseController {

    private final CourseService courtsService;

//    Press Shift + Shift
//    Type: Manage Licenses
//    Press Enter

    @PostMapping("/savCourse")

    public ResponseEntity<?> saveCourse(@RequestBody Course course, @RequestParam("userId") Long userId) {
        courtsService.saveCourse(course, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courtsService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courtsService.getCourseById(id));
    }

        @GetMapping("/user/{userId}")
    public  ResponseEntity<?> getAsscoitedCourse(@PathVariable("userId") Long userId){


            List<Course>list=courtsService.getCourseForGivenUser(userId);
        return  ResponseEntity.ok(list);
    }

    @DeleteMapping("/deleteCourse")
    public ResponseEntity<?>  deleteCourse(@RequestParam("id") Long id){

        courtsService.deleteCourseById(id);

        return  ResponseEntity.ok("successfully deleted course");
    }

}
