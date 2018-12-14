package com.iot.security.auth.biz;

import com.iot.security.auth.entity.ClientService;
import com.iot.security.auth.mapper.ClientServiceMapper;
import com.iot.security.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceBiz extends BaseBiz<ClientServiceMapper,ClientService> {
}
