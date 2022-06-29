package com.bjpowernode.controller;

import com.bjpowernode.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.UUID;

@RestController
public class LoginController {

    @Autowired
    public StringRedisTemplate redisTemplate;
    @GetMapping("doLogin")
    public String doLogin(String name,String pwd){
        System.out.println(name);
        System.out.println(pwd);
        User user = new User(1, "name", "pwd", 18);
        //token
        String token = UUID.randomUUID().toString();
        //存起来
        redisTemplate.opsForValue().set(token,user.toString(), Duration.ofSeconds(7200));
        return token;
    }
}
