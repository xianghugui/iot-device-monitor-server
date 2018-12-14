package com.iot.security.auth.feign;

import com.iot.security.api.vo.user.UserInfo;
import com.iot.security.auth.configuration.FeignConfiguration;
import com.iot.security.auth.util.user.JwtAuthenticationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "iot-admin",configuration = FeignConfiguration.class)
public interface IUserService {
  @RequestMapping(value = "/api/user/validate", method = RequestMethod.POST)
   UserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest);
}
