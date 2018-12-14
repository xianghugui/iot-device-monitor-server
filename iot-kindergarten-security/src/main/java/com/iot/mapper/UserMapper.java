package com.iot.mapper;

import com.iot.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface UserMapper extends Mapper<User> {
    /**
     *  分页查询用户数据
     * @param params
     * @return
     */
    List<HashMap> pageQueryUser (HashMap<String, Object> params);
    /**
     *  分页查询用户数据总数
     * @param params
     * @return
     */
    Long pageQueryUserTotal (HashMap<String, Object> params);
}
