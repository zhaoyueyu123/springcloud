package com.bjpowernode.controller;

import com.bjpowernode.domain.Order;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ParamController {

    @GetMapping("testUrl/{name}/and/{age}")
    public String testUrl(@PathVariable("name") String name,@PathVariable("age") Integer age){
        System.out.println(name+":"+age);
        return "testUrl";
    }

    @GetMapping("oneParam")
    public String oneParam(@RequestParam(required = false) String name){
        System.out.println(name);
        return "oneParam";
    }

    @GetMapping("towParam")
    public String towParam(@RequestParam(required = false) String name,@RequestParam(required = false) Integer age){
        System.out.println(name+":"+age);
        return "towParam";
    }
    @PostMapping("oneObj")
    public String oneObj(@RequestBody Order order){
        System.out.println(order);
        return "oneObj";
    }

    @PostMapping("oneObjOneParam")
    public String oneObjOneParam(@RequestBody Order order,@RequestParam("name") String name){
        System.out.println(order);
        System.out.println(name);
        return "oneObjOneParam";
    }

    @GetMapping("testTime")
    public String testTime(@RequestParam Date date){
        System.out.println(date);
        return "ok";
    }
}
