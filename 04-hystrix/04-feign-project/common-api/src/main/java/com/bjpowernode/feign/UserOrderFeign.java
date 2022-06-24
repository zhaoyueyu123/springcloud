package com.bjpowernode.feign;

import com.bjpowernode.domain.Order;
import com.bjpowernode.feign.hystrix.UserOrderFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="order-service",fallback = UserOrderFeignHystrix.class)
public interface UserOrderFeign {

    //查询订单
    @GetMapping("/order/getOrderByUserId")
    Order getOrderByUserId(@RequestParam Integer userId);

}
