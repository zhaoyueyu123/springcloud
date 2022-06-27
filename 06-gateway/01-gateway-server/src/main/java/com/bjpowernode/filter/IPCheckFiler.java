package com.bjpowernode.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.compilermsgs.qual.CompilerMessageKey;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import org.springframework.core.io.buffer.DataBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网关里面 过滤器
 * ip拦截器
 * 请求都有一个源头
 * 电话144 027 010
 * 请求------>gatewat------->service
 * 黑名单 白名单
 */
@Component
public class IPCheckFiler implements GlobalFilter, Ordered {

    /**
     * 网关的并发比较高 不要在网关里面直接操作mysql
     * 后台系统可以查询数据库,用户量并发量不大
     * 如果并发量大可以查redis
     */
    public static final List<String> BLACK_LIST = Arrays.asList("127.0.0.1");
    /**
     * 1.拿到IP
     * 2.校验ip是否符合规范
     * 3.放行/拦截
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
        ServerHttpRequest request = exchange.getRequest();
        String ip = request.getHeaders().getHost().getHostString();
        //查询数据库 看这个ip是否存在黑名单里面 mysql数据库的并发
        if(!BLACK_LIST.contains(ip)){
            return chain.filter(exchange);
        }
        //拦截
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("content-type","application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();
        map.put("code",438);
        map.put("msg","你是黑名单");
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = new byte[0];
        try {
            bytes = objectMapper.writeValueAsBytes(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(wrap));
    }

    @Override
    public int getOrder() {
        return -5;
    }
}
