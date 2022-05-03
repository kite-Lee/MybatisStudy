package com.lee.controller;

import com.lee.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    // localhost:8080/user/t1?name=
    @GetMapping("/t1")
    public String test01(String name, Model model) {

        // 1. 接收前端参数
        System.out.println("接收前端的参数为： " + name);
        // 2. 将返回的结果传递给前端
        model.addAttribute("msg", name);
        // 3. 视图跳转
        return "test";
    }

    // localhost:8080/user/t1?username=         // name 不行
    @GetMapping("/t2")
    public String test02(@RequestParam("username") String name, Model model) {

        // 1. 接收前端参数
        System.out.println("接收前端的参数为： " + name);
        // 2. 将返回的结果传递给前端
        model.addAttribute("msg", name);
        // 3. 视图跳转
        return "test";
    }

    // --------------------------------------------------------------------
    // 接收对象
    @GetMapping("/t3")
    public String test03(User user) {
        System.out.println(user);
        return "test";
    }
    // User(id=1, name=kite, age=10)

}
