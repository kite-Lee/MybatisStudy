package com.lee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c")
public class ControllerTest03 {

    @RequestMapping("at3")
    public String annoTest(Model model) {

        model.addAttribute("msg", "Double RequestMapping AnnoTest03");

        return "test";
    }
}
