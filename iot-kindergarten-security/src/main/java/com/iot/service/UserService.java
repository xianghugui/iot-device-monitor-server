package com.iot.service;

import com.iot.entity.User;
import com.iot.mapper.UserMapper;
import com.iot.security.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends BaseBiz<UserMapper, User> {

    /*
     * 功能描述: 新增接送人
     *
//     */
//    @Override
//    @Transactional
//    public void insertSelective(User user) {
//        user.setStatus(0);
//        user.setCreateTime(new Date());
//        super.insertSelective(user);
//    }
//
//    /*
//     * 功能描述: 删除接送人
//     *
//     */
//    @Transactional
//    public void deleteById(Long id) {
//        super.deleteById(id);
//    }
//
//    /*
//     * 功能描述: 查询某学生全部接送人
//     *
//     */
//    public List<Map> queryParent(Long student){
//        return mapper.queryParentByStudent(student);
//    }
//
//    /**
//     * 条件查询学生家长关联信息总数
//     * @param params
//     * @return
//     */
//    public int queryStudentByConditionTotal(HashMap<String, Object> params){
//        return mapper.queryAllParentByConditionTotal(params);
//    }
//
//    /**
//     * 查询未分配学校
//     * @return
//     */
//    public List<Map> listUnallocated() {
//        return mapper.listUnallocated();
//    }
//
//    /**
//     * 忘记密码时,判断接送人手机号是否存在
//     * @param studentId
//     * @param phone
//     * @return
//     */
//    public Long forgetPassWord(@Param("studentId")String studentId, @Param("phone")String phone){
//        return mapper.forgetPassWord(studentId,phone);
//    }
//
//    /**
//     * 根据姓名和手机查询数据是否重复
//     * @param name
//     * @param phone
//     * @return
//     */
//    public Long queryIsUnion(String name,String phone){
//        return mapper.queryIsUnion(name,phone);
//    }


    //--------------------------------分页查询节点园区数据begin------------------------------------------
    public List<HashMap> pageQueryUser(HashMap<String, Object> params){
        int pageIndex = Integer.valueOf(params.get("page").toString()) -1;
        int pageSize = Integer.valueOf(params.get("limit").toString());
        params.put("page", pageSize * pageIndex);
        params.put("limit", Integer.valueOf(params.get("limit").toString()));
        return mapper.pageQueryUser(params);
    }
    public Long pageQueryUserTotal(HashMap<String, Object> params){
        return mapper.pageQueryUserTotal(params);
    }
//--------------------------------------分页查询节点园区数据 end------------------------------------------
}
