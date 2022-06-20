package com.bjpowernode.feign.hystrix;

import com.bjpowernode.feign.CustomerRentFeign;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomerRentFeignHystrix implements CustomerRentFeign {

    /**
     * 备选方案
     * @return
     */
    @Override
    public String rent(){
        return "我是备胎";
    }
}
