package com.iot.mapper;

import com.iot.entity.Resources;
import tk.mybatis.mapper.common.Mapper;

/**
* 资源数据映射接口
* Created by generator 
*/
public interface ResourcesMapper extends Mapper<Resources> {
    Resources selectByMd5(String md5);
}
