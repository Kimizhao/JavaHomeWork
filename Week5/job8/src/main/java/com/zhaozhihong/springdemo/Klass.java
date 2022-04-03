package com.zhaozhihong.springdemo;

import lombok.Data;

import java.util.List;

/**
 * @author : zhaozh
 * @date : 2022/4/3 17:17
 */
@Data
public class Klass {
    List<Student> students;

    public void dong(){
        System.out.println(this.getStudents());
    }
}
