package com.bjpowernode.comtroller;

import com.bjpowernode.domain.Order;
import com.bjpowernode.feign.UserOrderFeign;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController implements UserOrderFeign {
    @Override
    public Order getOrderByUserId(Integer userId) {
        System.out.println(userId);
        Order order = new Order();
        order.setName("青椒肉丝");
        order.setPrice(15D);
        return order;
    }
}
