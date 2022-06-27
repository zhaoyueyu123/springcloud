package com.bjpowernode.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import org.springframework.core.io.buffer.DataBuffer;
import java.util.HashMap;

@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    /**
     * 这个就是过滤的方法
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //针对请求的过滤 拿到请求 header url 参数
        ServerHttpRequest request = exchange.getRequest();

        String path = request.getURI().getPath();
        System.out.println(path);
        HttpHeaders headers = request.getHeaders();
        System.out.println(headers);
        String methodName = request.getMethod().name();
        System.out.println(methodName);
        String hostName = request.getRemoteAddress().getHostName();
        System.out.println(hostName);
        String ip = request.getHeaders().getHost().getHostString();
        System.out.println(ip);
        //响应相关的数据
        ServerHttpResponse response = exchange.getResponse();
        //用了微服务肯定是前后端分离的,前后端分离替班通过json传送数据
        //{"code":200,"msg":"ok"}
        //设置编码 响应头里面设置
        response.getHeaders().set("content-type","application/json;charset=utf-8");
        //组装业务返回值
        HashMap<String,Object> map = new HashMap<>(4);
        map.put("code", HttpStatus.UNAUTHORIZED.value());
        map.put("msg","你未授权");
        ObjectMapper objectMapper = new ObjectMapper();
        //把一个map转成一个字节
        byte[] bytes = new byte[0];
        try {
            bytes = objectMapper.writeValueAsBytes(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //通过buffer工厂将字节数组包装成一个数据包
        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        //return response.writeWith(Mono.just(wrap));

        //放行到下一个过滤器
        return chain.filter(exchange);
    }

    /**
     * 指定顺序方法
     * 越小越先执行
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }
}
