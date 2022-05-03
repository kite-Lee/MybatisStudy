package com.lee.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 实现 Controller 接口定义控制器 （方法较老, 不能定义多个）
public class ControllerTest01 implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView();

        mv.addObject("msg", "Test from implements Controller");

        mv.setViewName("test");

        return mv;
    }
}
