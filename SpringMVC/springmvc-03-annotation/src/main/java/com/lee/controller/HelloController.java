package com.lee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/hello")
public class HelloController {

//    @RequestMapping("/h1")      // localhost .../hello/h1
    @RequestMapping("/hello")
    public String hello(Model model) {
        // 封装
        model.addAttribute("msg", "SpringMVC Annotation");

        return "hello"; // 会被视图解析器处理
    }

//    @RequestMapping("/h2")      // localhost .../hello/h2
    public String hello2(Model model) {
        // 封装
        model.addAttribute("msg", "SpringMVC Annotation");

        return "hello"; // 会被视图解析器处理
    }

}
