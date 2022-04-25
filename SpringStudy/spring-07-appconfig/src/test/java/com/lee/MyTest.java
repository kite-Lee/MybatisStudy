package com.lee;

import com.lee.config.LeeConfig;
import com.lee.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MyTest {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(LeeConfig.class);
        User user = context.getBean("getUser", User.class);
        System.out.println(user.getName());
    }
}
