package com.bjpowernode.controller;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @RequestMapping("test")
    @ResponseBody
    public String doDiscovery(String serviceName){
        List<ServiceInstance> instanceList = discoveryClient.getInstances(serviceName);
        instanceList.forEach(System.out::println);
        ServiceInstance serviceInstance = instanceList.get(0);
        String ip = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        System.out.println("ip="+ip+",port="+port);
        return instanceList.get(0).toString();
    }
}
