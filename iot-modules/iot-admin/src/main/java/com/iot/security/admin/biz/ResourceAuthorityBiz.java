package com.iot.security.admin.biz;

import com.iot.security.admin.entity.ResourceAuthority;
import com.iot.security.admin.mapper.ResourceAuthorityMapper;
import com.iot.security.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceAuthorityBiz extends BaseBiz<ResourceAuthorityMapper,ResourceAuthority> {
}
