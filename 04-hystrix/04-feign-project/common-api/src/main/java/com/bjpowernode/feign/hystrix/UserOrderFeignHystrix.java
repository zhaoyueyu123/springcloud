package com.bjpowernode.feign.hystrix;

import com.bjpowernode.domain.Order;
import com.bjpowernode.feign.UserOrderFeign;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserOrderFeignHystrix implements UserOrderFeign {
    /**
     * 一般远程调用的熔断可以直接返回null
     * @param userId
     * @return
     */
    @Override
    public Order getOrderByUserId(Integer userId) {
        return null;
    }
}
