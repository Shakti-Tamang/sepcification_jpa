package com.example.specification.service;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.specification.repository.UserRepostory;

import com.example.specification.model.usermodel;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepostory userRepostory;

    @Override
    public void saveUser(usermodel usermodel) {
        userRepostory.save(usermodel);
    }

    @Override
    public List<usermodel> getAllUsers() {
        return userRepostory.findAll();
    }

    @Override
    public usermodel getUserById(Long id) {
        return userRepostory.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void saveBulk(List<usermodel> list) {
        userRepostory.saveAll(list);
    }
}
