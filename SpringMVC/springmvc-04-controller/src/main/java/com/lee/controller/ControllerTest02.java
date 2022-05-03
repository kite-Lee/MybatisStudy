package com.lee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerTest02 {

    @RequestMapping("at1")
    public String AnnoTest01(Model model) {

        model.addAttribute("msg", "AnnoTest01 发来贺电");

        return "test";      // 待跳转的页面
    }

    @RequestMapping("at2")
    public String AnnoTest02(Model model) {

        model.addAttribute("msg", "AnnoTest02 发来贺电");

        return "test";      // 待跳转的页面
    }


}
