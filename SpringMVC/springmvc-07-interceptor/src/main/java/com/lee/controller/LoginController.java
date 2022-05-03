package com.lee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }


    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password) {
        session.setAttribute("userLoginInfo", username);
        session.setAttribute("username", username);
        return "main";
    }

    @RequestMapping("/main")
    public String mainPage() {
        return "main";
    }
    @RequestMapping("/logoff")
    public String logoff(HttpSession session, String username) {

        session.removeAttribute("userLoginInfo");
        return "redirect:/";
    }

}
