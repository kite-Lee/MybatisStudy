package com.lee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
// 关闭视图解析器

@Controller
public class ModelTest02 {

    @RequestMapping("/mvc")
    public String test2(Model model) {
        model.addAttribute("msg", "ModelTest02");
//        return "/WEB-INF/jsp/test.jsp";     // 转发
        return "forward:/WEB-INF/jsp/test.jsp";     // 转发
//        return "redirect:/index.jsp";   // 重定向
    }
}
