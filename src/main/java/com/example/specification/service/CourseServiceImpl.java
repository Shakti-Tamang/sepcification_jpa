package com.example.specification.service;

import java.util.ArrayList;
import java.util.List;

import com.example.specification.model.Product;
import com.example.specification.specification.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.specification.model.Course;
import com.example.specification.repository.CourseRepo;
import com.example.specification.repository.UserRepostory;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements  CourseService {
    
    private final UserRepostory userRepostory;

    private final CourseRepo courseRepo;
    @Override


    public void saveCourse(Course course, Long userId) {
        var user = userRepostory.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (course.getUsers() == null) {
            course.setUsers(new ArrayList<>());
        }
        if (!course.getUsers().contains(user)) {
            course.getUsers().add(user);
        }

        if (user.getCourses() == null) {
            user.setCourses(new ArrayList<>());
        }
        if (!user.getCourses().contains(course)) {
            user.getCourses().add(course);
        }

        courseRepo.save(course);

    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public List<Course> getCourseForGivenUser(Long UserId) {
//        return List.of();

//        List<Integer>all=List.of(1,2,3,4,5);


        Specification<Course>spec=((Specification<Course>) (root, query, cb) -> cb.conjunction()).and(ProductSpecification.CourseForUser(UserId));

        List<Course>list=courseRepo.findAll(spec);


        return list.isEmpty()?List.of():list;
    }

}
