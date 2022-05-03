package com.lee.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.pojo.User;
import com.lee.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Controller
@RestController     // 注解后不会走视图解析器，会直接返回字符串
public class UserController {

    // 解决乱码问题
    // @RequestMapping(value = "/j1", produces = "application/json;charset=utf-8")

    @RequestMapping("/j1")
//    @ResponseBody   // 注解后不会走 视图解析器，会直接返回字符串
    public String json01() throws JsonProcessingException {
        // jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        // 创建一个对象
        User user = new User("Kite", 25, "男");

        String str = mapper.writeValueAsString(user);

        System.out.println(str);

        // {"name":"Kite","age":25,"sex":"男"}
        return str;

    }

    @RequestMapping("/j2")
    public String json02() throws JsonProcessingException {
        // jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        List<User> userList = new ArrayList<>();
        // 创建一个对象
        User user1 = new User("Kite", 25, "男");
        User user2 = new User("Kite", 25, "男");
        User user3 = new User("Kite", 25, "男");
        User user4 = new User("Kite", 25, "男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        String str = mapper.writeValueAsString(userList);

        System.out.println(str);
        return str;
        /*
            [
            {"name":"Kite","age":25,"sex":"男"},
            {"name":"Kite","age":25,"sex":"男"},
            {"name":"Kite","age":25,"sex":"男"},
            {"name":"Kite","age":25,"sex":"男"}
            ]
        */
    }


    /*
           java 基本方法修改时间输出格式
     */
    @RequestMapping("/j3")
    public String json03() throws JsonProcessingException {
        // jackson ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        // 时间戳
        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // yyyy-MM-dd HH:mm:ss
        return mapper.writeValueAsString(simpleDateFormat.format(date));
    }

    /*
           修改 mapper 时间输出格式
     */
    @RequestMapping("/j4")
    public String json04() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // 时间戳
        Date date = new Date();

        // 时间输出格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 修改 mapper 时间输出格式
        mapper.setDateFormat(simpleDateFormat);

        // yyyy-MM-dd HH:mm:ss
        return mapper.writeValueAsString(date);

    }

    @RequestMapping("/j5")
    public String json05() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        // 时间戳
        Date date = new Date();

        return JsonUtils.getJson(date);
        // 自定义 输出时间格式
        // return JsonUtils.getJson(date,"yyyy-MM-dd HH:mm:ss");
    }

    @RequestMapping("/j22")
    public String json06() throws JsonProcessingException {

        List<User> userList = new ArrayList<>();
        // 创建一个对象
        User user1 = new User("Kite", 25, "男");
        User user2 = new User("Kite", 25, "男");
        User user3 = new User("Kite", 25, "男");
        User user4 = new User("Kite", 25, "男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        return JsonUtils.getJson(userList);
    }

    /*
            fastjson
     */
    @RequestMapping("/j7")
    public String json07() throws JsonProcessingException {

        List<User> userList = new ArrayList<>();
        // 创建一个对象
        User user1 = new User("Kite", 25, "男");
        User user2 = new User("Kite", 25, "男");
        User user3 = new User("Kite", 25, "男");
        User user4 = new User("Kite", 25, "男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        String string = JSON.toJSONString(userList);
        return string;
    }
}
