package com.example.specification.service;
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
}
