package com.lee;

import com.lee.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {

        // 获取 Spring 的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 取出对象
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello);
    }
}
