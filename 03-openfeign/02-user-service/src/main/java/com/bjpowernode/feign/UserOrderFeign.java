package com.bjpowernode.feign;

import com.bjpowernode.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@FeignClient(value = "order-service")//value是提供者的服务名称
public interface UserOrderFeign {
    @GetMapping("testOrder")
    public String doOrder();

    @GetMapping("testUrl/{name}/and/{age}")
    public String testUrl(@PathVariable("name") String name, @PathVariable("age") Integer age);

    @GetMapping("oneParam")
    public String oneParam(@RequestParam(required = false) String name);

    @GetMapping("towParam")
    public String towParam(@RequestParam(required = false) String name,@RequestParam(required = false) Integer age);
    @PostMapping("oneObj")
    public String oneObj(@RequestBody Order order);

    @PostMapping("oneObjOneParam")
    public String oneObjOneParam(@RequestBody Order order,@RequestParam("name") String name);

    @GetMapping("testTime")
    public String testTime(@RequestParam Date date);
}
