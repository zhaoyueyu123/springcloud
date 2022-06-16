package com.bjpowernode.controller;

import com.bjpowernode.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("testGet")
    @ResponseBody
    public String get(String name){
        System.out.println(name);
        return "ok";
    }

    @PostMapping("testPost1")
    @ResponseBody
    public String testPost1(@RequestBody User user){
        System.out.println(user);
        return "ok";
    }

    @PostMapping("testPost2")
    @ResponseBody
    public String testPost2(User user){
        System.out.println(user);
        return "ok";
    }
}
