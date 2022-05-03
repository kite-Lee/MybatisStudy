package com.lee.controller;

import com.lee.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AjaxController {

    @RequestMapping("/t1")
    public String test01() {

        return "hello";
    }

    @RequestMapping("/a1")
    public void test02(String name, HttpServletResponse resp) throws IOException {
        System.out.println("a1.name - - > " + name);
        if (name.equals("Kite")) {
            resp.getWriter().println("true!");
        } else {
            resp.getWriter().println("false!");
        }
    }

    @RequestMapping("/a2")
    public List<User> test03() {
        List<User> userList = new ArrayList<>();

        // 添加数据
        userList.add(new User("诸葛孔明", 39, "男"));
        userList.add(new User("诸葛村夫", 13, "男"));
        userList.add(new User("诸葛钢铁", 16, "女"));
        userList.add(new User("诸子百家", 100, "男"));

        return userList;
    }

    @RequestMapping("/a3")
    public String test04(String name, String pwd) {
        String msg = "";
        if (name != null) {
            // 这些数据应该在数据库中查
            if ("admin".equals(name)) {
                msg = "ok";
            } else {
                msg = "用户名有误";
            }
        }
        if (pwd != null) {
            // 这些数据应该在数据库中查
            if ("123456".equals(pwd)) {
                msg = "ok";
            } else {
                msg = "密码有误";
            }
        }
        return msg;
    }

}
