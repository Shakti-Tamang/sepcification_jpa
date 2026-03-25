package com.example.specification.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LongestString {



    public String biggestAll(){

        List<String>list=Arrays.asList("java","python","c++","javascript","ruby");
        List<String>end=List.of("java","python","c++","javascript","ruby");


        String longestString=list.stream().max(Comparator.comparingInt(String::length)).orElse(null);

        System.out.println(longestString);



        int a[]={1,2,5,4,3};

        Arrays.sort(a);

        System.out.println(a.toString());
        return longestString;

    }
}
