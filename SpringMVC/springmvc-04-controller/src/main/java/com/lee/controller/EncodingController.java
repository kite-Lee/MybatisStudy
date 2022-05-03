package com.lee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EncodingController {

    // 过滤器解决乱码
    @PostMapping("/e/table")
    public String test01(String name, Model model) {
        model.addAttribute("msg", name);
        System.out.println(name);
        return "test";
    }
}
