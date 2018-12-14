package com.iot.security.admin.biz;

import org.springframework.stereotype.Service;

import com.iot.security.admin.entity.GroupType;
import com.iot.security.admin.mapper.GroupTypeMapper;
import com.iot.security.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class GroupTypeBiz extends BaseBiz<GroupTypeMapper,GroupType> {
}
