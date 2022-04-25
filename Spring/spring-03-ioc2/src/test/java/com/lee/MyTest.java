package com.lee;

import com.lee.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {

        // User user = new User();
        // sout : User的无参构造

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 无参构造
        User user = (User) context.getBean("user");
        user.show();
        // 有参构造
        User userIndex = (User) context.getBean("userIndex");
        // User userIndex2 = (User) context.getBean("index");
        userIndex.show();
        User userType = (User) context.getBean("userType");
        userType.show();
        User userName = (User) context.getBean("userName");
        userName.show();
    }
}
