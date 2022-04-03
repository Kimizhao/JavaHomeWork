package com.zhaozhihong.springdemo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author : zhaozh
 * @date : 2022/4/3 17:16
 */
public class School implements ISchool{
    // Resource
    @Autowired(required = true) //primary
            Klass class1;

    @Resource(name = "student100")
    Student student100;

    @Override
    public void ding(){

        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);

    }
}
