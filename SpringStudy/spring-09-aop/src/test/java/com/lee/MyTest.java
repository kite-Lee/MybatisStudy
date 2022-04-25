package com.lee;

import com.lee.service.UserService;
import com.lee.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // UserServiceImpl userService = context.getBean("userService", UserServiceImpl.class);     // 错误写法
        // 动态代理代理的是接口
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
        UserService userService = context.getBean("userService2", UserService.class);
        userService.add();
    }

    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");
        UserService userService = context.getBean("userService3", UserService.class);
        userService.add();
    }
}
