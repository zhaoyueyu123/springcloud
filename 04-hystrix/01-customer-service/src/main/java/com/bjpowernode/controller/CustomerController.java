package com.bjpowernode.controller;

import com.bjpowernode.feign.CustomerRentFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CustomerController {

    @Resource
    private CustomerRentFeign customerRentFeign;

    @GetMapping("customerRent")
    public String CustomerRent(){
        System.out.println("客户来租车了");
        String rent = customerRentFeign.rent();
        return rent;
    }
}
