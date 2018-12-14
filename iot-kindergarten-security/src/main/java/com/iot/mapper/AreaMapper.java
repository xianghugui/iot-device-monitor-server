package com.iot.mapper;

import com.alibaba.fastjson.JSONArray;
import com.iot.entity.Area;
import tk.mybatis.mapper.common.Mapper;

public interface AreaMapper extends Mapper<Area> {
    /**
     * 查询区域表所有数据构成区域树
     * @return
     */
    JSONArray queryAllAreaTreeNode();

    /**
     * 查询有学校的所有省市区节点数据
     * @return
     */
    JSONArray areaSchoolList();
    /**
     * 根据区域ID更新区域状态为1
     * @param id
     */
    void updateStatusByAreaId(Integer id);

    /**
     * 根据区域ID更新区域状态为0
     * @param id
     */
    void updateDeleteStatusByAreaId(Integer id);

}
