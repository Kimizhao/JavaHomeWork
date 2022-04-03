package com.zhaozhihong.springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : zhaozh
 * @date : 2022/4/3 16:59
 */
public class SpringDemo01Application {
    public static void main(String[] args) {
        long s =  System.currentTimeMillis();

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Student student123 = context.getBean(Student.class);

        Student student123 = (Student) context.getBean("student123");
        System.out.println(student123.toString());

        student123.print();

    }
}
