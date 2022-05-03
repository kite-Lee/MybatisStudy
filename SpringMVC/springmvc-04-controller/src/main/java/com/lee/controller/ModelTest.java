package com.lee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class ModelTest {

    @RequestMapping("/mt1")
    public String test1(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        System.out.println(session.getId());    // 获取 id

        return "test";
    }

    @RequestMapping("/mt2")
    public void test2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 转发 (URL不变)
        request.getRequestDispatcher("/postMappingTest.jsp").forward(request, response);
    }

    @RequestMapping("/mt3")
    public void test3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 重定向 (URL改变)
        response.sendRedirect("/postMappingTest.jsp");

    }
}
