package com.zhaozhihong.springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : zhaozh
 * @date : 2022/4/3 16:59
 */
public class SpringDemo02Application {
    public static void main(String[] args) {
        long s =  System.currentTimeMillis();

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


        ISchool school1 = context.getBean(ISchool.class);
        System.out.println(school1);
        System.out.println("ISchool接口的对象AOP代理后的实际类型："+school1.getClass());

        school1.ding();

        System.out.println("   context.getBeanDefinitionNames() ===>> "+ String.join(",", context.getBeanDefinitionNames()));



    }
}
