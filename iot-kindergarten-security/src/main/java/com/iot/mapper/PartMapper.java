package com.iot.mapper;

import com.alibaba.fastjson.JSONArray;
import com.iot.entity.Part;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface PartMapper extends Mapper<Part> {
    /**
     * 根据条件分页查询园区信息
     * @param params 分页信息  包括查询条件
     * @return
     */
    List<HashMap> querySchoolByNodeId (HashMap<String, Object> params);

    /**
     * 根据条件分页查询园区信息总数
     * @param params
     * @return
     */
    Integer querySchoolByNodeIdTotal(HashMap<String, Object> params);

    /**
     * 根据节点ID查询当前节点下是否存在园区数据
     * @param id
     * @return
     */
    Integer queryByNodeId(Integer id);

    /**
     * 查询所有的负责人用户
     * @return
     */
    List<HashMap> schoolUser();


    /**
     * 查询费用
     * @param partId
     * @return
     */
    Integer queryCost(Long partId);

    /**
     * 根据区域节点分页查询园区信息
     * @param params 分页信息  包括查询条件
     * @return
     */
    List<HashMap> pageQuerySchoolByNodeId (HashMap<String, Object> params);

    /**
     * 根据区域节点分页查询园区信息总数
     * @param params
     * @return
     */
    Integer pageQuerySchoolByNodeIdTotal(HashMap<String, Object> params);

    /**
     * 根据学校ID查询讯学校数据
     * @param id
     * @return
     */
    HashMap querySchoolBySchoolId(Long id);

    /**
     * 查询有效期
     * @param partId
     * @return
     */
    Integer queryValidityTime(Long partId);

    /**
     * 根据用户id查询学校ID
     * @param id
     * @return
     */
    Long selectSchoolIdByUserId(Long id);

    Long queryHasCode(String code);


}
