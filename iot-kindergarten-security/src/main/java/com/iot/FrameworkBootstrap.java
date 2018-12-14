package com.iot;

import com.iot.security.auth.client.EnableAceAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.iot.mapper")
@EnableFeignClients({"com.iot.security.auth.client.feign"})
@EnableAceAuthClient
public class FrameworkBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(FrameworkBootstrap.class, args);
    }
}
