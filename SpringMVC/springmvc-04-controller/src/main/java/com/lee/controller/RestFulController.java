package com.lee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestFulController {

    @RequestMapping("/add")
    public String restFulTest(int a, int b, Model model) {
        int res = a + b;
        model.addAttribute("msg", "not restful - - > res = " + res);
        return "test";
    }
    // http://localhost:8080/add?a=1&b=2 //     not restful - > res = 3

    @RequestMapping("/add/{a}/{b}")
    public String restFulTest2(@PathVariable int a, @PathVariable int b, Model model) {
        int res = a + b;
        model.addAttribute("msg", "restful - - > res = " + res);
        return "test";
    }
//     http://localhost:8080/add/1/2 //     not restful - > res = 3


//    @RequestMapping(value="/add/{a}/{b}",method=RequestMethod.GET)
//    public String restFulTest3(@PathVariable int a, @PathVariable int b, Model model) {
//        int res = a + b;
//        model.addAttribute("msg", "RequestMapping GET restful - - > res = " + res);
//        return "test";
//    }

    @RequestMapping(value="/add/{a}/{b}",method=RequestMethod.POST)
    public String restFulTest4(@PathVariable int a, @PathVariable int b, Model model) {
        int res = a + b;
        model.addAttribute("msg", "RequestMapping POST restful - - > res = " + res);
        return "test";
    }
    // Request method 'GET' not supported

    @GetMapping("/add/{a}/{b}")
    public String restFulTest5(@PathVariable int a, @PathVariable int b, Model model) {
        int res = a + b;
        model.addAttribute("msg", "GetMapping restful - - > res = " + res);
        return "test";
    }
}
