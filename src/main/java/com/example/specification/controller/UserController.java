package com.example.specification.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.specification.service.UserService;

import com.example.specification.model.usermodel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

        private final UserService userService;

        @PostMapping("/saveUser")
        public ResponseEntity<?> saveUser(@RequestBody usermodel entity) {
            userService.saveUser(entity);

            return ResponseEntity.ok("success");
        }

        @PostMapping("/bulkSave")

        public ResponseEntity<?> saveMultipleUser(List<usermodel> list){
            userService.saveBulk(list);
            return  ResponseEntity.ok("successfully saved");
    }



        @GetMapping("/users")
        public ResponseEntity<?> getAllUsers() {
            return ResponseEntity.ok(userService.getAllUsers());
        }

        @GetMapping("/users/{id}")
        public ResponseEntity<?> getUserById(@PathVariable Long id) {
            return ResponseEntity.ok(userService.getUserById(id));
        }

}
