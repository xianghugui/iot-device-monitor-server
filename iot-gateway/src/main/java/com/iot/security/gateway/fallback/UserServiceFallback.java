package com.iot.security.gateway.fallback;

import com.iot.security.gateway.feign.IUserService;
import com.iot.security.api.vo.authority.PermissionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
@Slf4j
public class UserServiceFallback implements IUserService {

    @Override
    public List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username) {
        log.error("调用{}异常{}","getPermissionByUsername",username);
        return null;
    }

    @Override
    public List<PermissionInfo> getAllPermissionInfo() {
        log.error("调用{}异常","getAllPermissionInfo");
        return null;
    }
}
