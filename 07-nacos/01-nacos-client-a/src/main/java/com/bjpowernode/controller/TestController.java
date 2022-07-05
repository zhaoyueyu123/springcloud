package com.bjpowernode.controller;

import com.bjpowernode.feign.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    public DiscoveryClient discoveryClient;

    @Autowired
    public TestFeign testFeign;
    @GetMapping("test")
    public String test(){
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        System.out.println(instances);
        return testFeign.info();
    }
}
