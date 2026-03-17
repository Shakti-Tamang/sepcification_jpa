package com.example.specification.service;


import java.util.List;

import com.example.specification.model.usermodel;
public interface UserService {

    public void saveUser(usermodel usermodel);

    public List<usermodel> getAllUsers();

    public usermodel getUserById(Long id);

}
