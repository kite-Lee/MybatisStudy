package com.lee.controller;

import com.lee.pojo.Book;
import com.lee.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    // controller 调用 service
    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    // 查询全部的书 并返回到一个数据展示页面
    @RequestMapping("/allBooks")
    public String listBook(Model model) {

        List<Book> bookList = bookService.getAllBooks();

        model.addAttribute("list", bookList);
        return "allBooks";
    }


    // 跳转至新增书籍页面
    @RequestMapping("/toAddBook")
    public String toAddBookPage() {

        return "addBook";
    }

    // 新增书籍
    @RequestMapping("/addBook")
    public String addBook(Book book) {
        bookService.addBook(book);
        return "redirect:/book/allBooks";
    }

    // 跳转至修改书籍页面
    @RequestMapping("/toUpdateBook")
    public String toUpdateBookPage(int id, Model model) {
        // System.out.println("toUpdateBook");
        Book book = bookService.getBookById(id);

        model.addAttribute("GotBook", book);
        return "updateBook";
    }

    // 修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Book book) {
        bookService.updateBook(book);
        return "redirect:/book/allBooks";
    }


    // 删除书籍
    @RequestMapping("/deleteBook/{bookId}")
    public String updateBook(@PathVariable("bookId") int id) {
        bookService.deleteBook(id);
        return "redirect:/book/allBooks";
    }

    // 查询书籍
    @RequestMapping("/getBookByName")
    public String getBook(String bookName, Model model) {
        System.out.println("--------------------" + bookName);
        List<Book> bookList = bookService.getBookByName(bookName);
        if (bookList.isEmpty()) {
            model.addAttribute("error", "true");
        } else {
            model.addAttribute("list", bookList);
        }
        return "allBooks";
    }
}
