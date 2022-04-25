package com.lee.dao;

import com.lee.service.UserService;
import com.lee.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {

//        UserServiceImpl userService = new UserServiceImpl();
//        userService.setUserDao(new UserDaoMysqlImpl());
//        userService.getUser();
//        userService.setUserDao(new UserDaoOracleImpl());
//        userService.getUser();
//        userService.setUserDao(new UserDaoImpl());
//        userService.getUser();

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("userServiceMysqlImpl");
        userServiceImpl.getUser();
        UserServiceImpl userServiceImpl2 = (UserServiceImpl) context.getBean("userServiceOracleImpl");
        userServiceImpl2.getUser();
    }
}
