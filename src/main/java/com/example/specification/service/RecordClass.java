package com.example.specification.service;


import com.example.specification.model.Course;
import com.example.specification.model.usermodel;
import com.example.specification.records.shaktirecords;
import org.springframework.stereotype.Service;

@Service
public class RecordClass {

    usermodel usermodel=new usermodel();
    public void test(){

        System.out.println(usermodel.getName());
        shaktirecords shaktirecords1 = new shaktirecords("shakti", 1234567890L, 25);

//        shaktirecords1.age();

        System.out.println(shaktirecords1.toString());
    }

}
