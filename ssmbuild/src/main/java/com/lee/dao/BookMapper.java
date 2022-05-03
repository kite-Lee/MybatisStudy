package com.lee.dao;

import com.lee.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {

    // 增加一本书
    int addBook(Book book);

    // 删除一本书
    int deleteBook(@Param("bookID") int id);

    // 更新一本书
    int updateBook(Book book);

    // 查询一本书
    Book getBookById(@Param("bookID") int id);

    // 查询全部的书
    List<Book> getAllBooks();

    // 查询
    List<Book> getBookByName(String bookName);

}
