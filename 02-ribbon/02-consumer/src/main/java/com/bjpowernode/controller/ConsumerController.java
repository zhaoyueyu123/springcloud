package com.bjpowernode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @GetMapping("testRibbon")
    public String testRibbon(String serviceName){
        //需要拿到ip,端口和路径
        String result = restTemplate.getForObject("http://"+serviceName+"/hello",String.class);
        //只要你给restTemplate,加了ribbon的注解，项目中这个对象发起的请求都会走ribbon的代理
        //如果你想使用元素的restTemplate就需要重新创建一个对象
//        RestTemplate myRest = new RestTemplate();
//        String foObject = myRest.getForObject("http://localhost:8888/aaa",String.class);
        return result;
    }

    @GetMapping("testRibbonRule")
    public String testRibbonRule(String serviceName){
        ServiceInstance choose = loadBalancerClient.choose(serviceName);
        return choose.toString();

    }
}
