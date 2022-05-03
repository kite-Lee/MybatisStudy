package com.lee.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ModelAndView 模型和视图
        ModelAndView mv = new ModelAndView();
        // 封装对象
        mv.addObject("msg", "HelloSpringMVC");

        // 因为已经在配置中添加了前缀 "WEB-INF/jsp/" 后缀".jsp"  所以下式等价于 WEB-INF/jsp/hello.jsp
        mv.setViewName("hello");

        return mv;
    }
}
