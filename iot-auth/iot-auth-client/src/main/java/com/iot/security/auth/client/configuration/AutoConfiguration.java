package com.iot.security.auth.client.configuration;

import com.iot.security.auth.client.config.ServiceAuthConfig;
import com.iot.security.auth.client.config.UserAuthConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
//@ComponentScan({"com.iot.security.auth.client","com.iot.security.auth.common.event"})
@ComponentScan({"com.iot.security.auth.client"})
public class AutoConfiguration {
    @Bean
    ServiceAuthConfig getServiceAuthConfig(){
        return new ServiceAuthConfig();
    }

    @Bean
    UserAuthConfig getUserAuthConfig(){
        return new UserAuthConfig();
    }

}
