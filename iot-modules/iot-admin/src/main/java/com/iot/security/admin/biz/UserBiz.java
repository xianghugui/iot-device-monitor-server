package com.iot.security.admin.biz;

import com.iot.cache.annotation.Cache;
import com.iot.cache.annotation.CacheClear;
import com.iot.security.admin.entity.User;
import com.iot.security.admin.mapper.MenuMapper;
import com.iot.security.admin.mapper.UserMapper;
import com.iot.security.auth.client.jwt.UserAuthUtil;
import com.iot.security.common.biz.BaseBiz;
import com.iot.security.common.constant.UserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserBiz extends BaseBiz<UserMapper,User> {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserAuthUtil userAuthUtil;
    @Override
    public void insertSelective(User entity) {
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getPassword());
        entity.setPassword(password);
        super.insertSelective(entity);
    }

    @Override
    @CacheClear(pre="user{1.username}")
    public void updateSelectiveById(User entity) {
        super.updateSelectiveById(entity);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     *
     * @Cache(key="user{1}") 该注解会引起登陆失败，暂时注掉， 后期优化
     */
//    @Cache(key="user{1}")
    public User getUserByUsername(String username){
        User user = new User();
        user.setUsername(username);
        return mapper.selectOne(user);
    }

    public Long selectPartIdByUserId(Long id) {
        return mapper.selectPartIdByUserId(id);
    }



    public List<HashMap> queryUserByUserName(HashMap<String, Object> params){
        int pageIndex = Integer.valueOf(params.get("page").toString()) -1;
        int pageSize = Integer.valueOf(params.get("limit").toString());
        params.put("page", pageSize * pageIndex);
        params.put("limit", Integer.valueOf(params.get("limit").toString()));
        return mapper.queryUserByUserName(params);
    }
    public int queryUserByUserNameTotal(HashMap<String, Object> params){
        return mapper.queryUserByUserNameTotal(params);
    }
}
