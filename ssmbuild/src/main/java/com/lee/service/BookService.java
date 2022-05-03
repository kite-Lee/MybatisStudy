package com.lee.service;

import com.lee.pojo.Book;

import java.util.List;

public interface BookService {

    // 增加一本书
    int addBook(Book book);

    // 删除一本书
    int deleteBook(int id);

    // 更新一本书
    int updateBook(Book book);

    // 查询一本书
    Book getBookById(int id);

    // 查询全部的书
    List<Book> getAllBooks();

    // 根据书名查询
    List<Book> getBookByName(String bookName);

}
