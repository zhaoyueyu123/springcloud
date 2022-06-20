package com.bjpowernode.controller;

import com.bjpowernode.domain.Order;
import com.bjpowernode.feign.UserOrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {

    @Autowired
    public UserOrderFeign userOrderFeign;

    /**
     * feign的默认等待时间是1秒，超过1秒直接报错超时
     */
    @GetMapping("userDoOrder")
    public String userDoOrder(){
        System.out.println("有用户进来了");
        return userOrderFeign.doOrder();
    }

    @GetMapping("testParam")
    public String testParam(){
        String cxs = userOrderFeign.testUrl("cxs",18);
        System.out.println(cxs);
        String t = userOrderFeign.oneParam("老唐");
        System.out.println(t);
        String lg = userOrderFeign.towParam("lei",30);
        System.out.println(lg);
        Order order = Order.builder().name("牛排").price(188D).time(new Date()).id(1).build();
        String s = userOrderFeign.oneObj(order);
        System.out.println(s);
        String param = userOrderFeign.oneObjOneParam(order,"jige");
        System.out.println(param);
        return "ok";
    }

    /**
     * 1.不建议单独传时间
     * 2.转成字符串 2022-03-20 10:25:55:213
     * 3.jdk localDate年月日 LocalDateTime 会丢失秒
     * @return
     */
    @GetMapping("time")
    public String time(){
        Date date = new Date();
        System.out.println(date);
        String s = userOrderFeign.testTime(date);
        return s;
    }
}
