package com.lee;

import com.lee.pojo.Book;
import com.lee.service.BookService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    @Test
    public void test01() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService service = context.getBean("bookServiceImpl",BookService.class);
        List<Book> bookList = service.getAllBooks();

        for (Book book : bookList) {
            System.out.println(book);
        }
    }
}
