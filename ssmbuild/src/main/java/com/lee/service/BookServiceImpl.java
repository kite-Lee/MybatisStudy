package com.lee.service;

import com.lee.dao.BookMapper;
import com.lee.pojo.Book;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service      // BookServiceImpl 添加 @Service 就可以被 spring-service 扫描到
public class BookServiceImpl implements BookService{

    // service 调用 dao
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int deleteBook(int id) {
        return bookMapper.deleteBook(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public Book getBookById(int id) {
        return bookMapper.getBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }

    @Override
    public List<Book> getBookByName(String bookName) {
        return bookMapper.getBookByName(bookName);
    }

}

