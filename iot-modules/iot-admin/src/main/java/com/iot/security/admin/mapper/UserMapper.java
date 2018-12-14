package com.iot.security.admin.mapper;

import com.iot.security.admin.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface UserMapper extends Mapper<User> {
     List<User> selectMemberByGroupId(@Param("groupId") int groupId);
     List<User> selectLeaderByGroupId(@Param("groupId") int groupId);
     Long selectPartIdByUserId(Long id);

    /**
     * 根据条件分页查询用户信息
     * @param params 分页信息  包括查询条件
     * @return
     */
    List<HashMap> queryUserByUserName (HashMap<String, Object> params);

    /**
     * 根据条件分页查询用户信息总数
     * @param params
     * @return
     */
    Integer queryUserByUserNameTotal(HashMap<String, Object> params);
}