package com.bjpowernode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BTestController {

    @GetMapping("info")
    public String info(){
        return "年薪20w";
    }
}
