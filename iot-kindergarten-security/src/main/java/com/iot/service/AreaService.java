package com.iot.service;

import com.alibaba.fastjson.JSONArray;
import com.iot.entity.Area;
import com.iot.mapper.AreaMapper;
import com.iot.security.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.iot.util.TreeUtil.listToTree;

@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor = Exception.class)
public class AreaService extends BaseBiz<AreaMapper, Area> {
    @Autowired
    public AreaMapper areaMapper;
    /**
     * 构建区域树
     * @return
     */
    public JSONArray queryAllAreaTreeNode() {

       JSONArray jsonArrayList = areaMapper.queryAllAreaTreeNode();
        JSONArray result = listToTree(jsonArrayList,"id","parentId","children");
        return result;
    }

    /**
     * 查询有学校的所有省市区节点数据
      * @return
     */
    public JSONArray areaSchoolList() {
        JSONArray jsonArrayList = areaMapper.areaSchoolList();
        JSONArray result = listToTree(jsonArrayList,"id","parentId","children");
        return result;
    }
    /**
     * 更新区域状态
     * @param areaIdList
     */
  public   void updateStatusByAreaId(Integer [] areaIdList){
        for (int i = 0 ;i < areaIdList.length; i ++){
            areaMapper.updateStatusByAreaId(areaIdList[i]);
        }
    }

    /**
     * 更具节点ID删除节点
     * @param id
     */
    public   void updateDeleteStatusByAreaId(Integer id){
            areaMapper.updateDeleteStatusByAreaId(id);

    }

}
