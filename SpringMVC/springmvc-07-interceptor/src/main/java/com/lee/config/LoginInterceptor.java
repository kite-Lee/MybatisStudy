package com.lee.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        /*
            放行 判断什么情况下访问主页
                1. 登录页面可访问
                2. 已经登录过的
         */

        // 1. 登录页面可访问
        if (request.getRequestURI().contains("toLogin") || request.getRequestURI().contains("login")) {
            System.out.println(request.getRequestURI());
            return true;
        }

        // 2. 已经登录过的
        if (session.getAttribute("userLoginInfo") != null) {
            System.out.println("session OK");
            return true;
        }
        // 其他情况重定向登录页面
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }
}
