package com.lee;

import com.lee.pojo.Student;
import com.lee.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void SetDITest() {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);

    }

    @Test
    public void P_C_DITest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user = context.getBean("user", User.class);
        User user2 = (User) context.getBean("user2");
        User user3 = context.getBean("user3", User.class);

        System.out.println(user);
        System.out.println(user2);
        System.out.println(user3);
    }
}
