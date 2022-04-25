package com.lee;

import com.lee.pojo.People;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        People people = context.getBean("people", People.class);
        // People people = context.getBean("people2", People.class);
        // People people = context.getBean("people3", People.class);
        people.getCat().shout();
        people.getDog().shout();
        System.out.println(people.getName());
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans2.xml");
        People people = context.getBean("people", People.class);
        // People people = context.getBean("people2", People.class);
        // People people = context.getBean("people3", People.class);
        people.getCat().shout();
        people.getDog().shout();
        System.out.println(people.getName());
    }
}
