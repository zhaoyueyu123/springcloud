package com.bjpowernode.config;

import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder){
        return builder.routes().route("guochuang-id",r->r.path("/guochuang").uri("https://bilibili.com"))
                .route("kichiku-id",r->r.path("/v/kichiku").uri("https://bilibili.com"))
                .build();
    }

    public void main(String[] args){
        System.out.println(ZonedDateTime.now());
    }
}
