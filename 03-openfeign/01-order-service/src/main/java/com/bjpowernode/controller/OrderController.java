package com.bjpowernode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("testOrder")
    public String doOrder(){
        return "Order";
    }
}
