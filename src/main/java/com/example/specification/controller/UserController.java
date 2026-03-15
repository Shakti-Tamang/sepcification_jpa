package com.example.specification.controller;



import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.example.specification.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.specification.model.usermodel;
import lombok.RequiredArgsConstructor;

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

}
