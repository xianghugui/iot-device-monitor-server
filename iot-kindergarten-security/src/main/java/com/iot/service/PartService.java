package com.iot.service;

import com.iot.entity.Part;
import com.iot.mapper.AreaMapper;
import com.iot.mapper.PartMapper;
import com.iot.security.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor = Exception.class)
public class PartService extends BaseBiz<PartMapper, Part> {
    @Autowired
    private PartMapper partMapper;

    @Autowired
    private AreaMapper areaMapper;

    /*
     * 功能描述: 新增园区
     *
     */
    @Override
    public void insertSelective(Part part) {
        part.setCreateTime(new Date());
        part.setId(System.nanoTime());
        super.insertSelective(part);
    }

    /**
     * 查询校园编码是否重复
     * @param code
     * @return
     */
    public Long queryHasCode(String code){
        return partMapper.queryHasCode(code);
    }

    /*
     * 功能描述: 修改园区
     *
     */
    @Override
    public void updateSelectiveById(Part part) {
        super.updateSelectiveById(part);
    }

    /*
     * 功能描述: 删除园区
     *
     */
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    /*
     * 功能描述: 根据主键查询园区
     *
     */
    public Part selectById(Long id) {
        return super.selectById(id);
    }

    //--------------------------------分页查询节点园区数据begin------------------------------------------
    public List<HashMap> querySchoolByCondition(HashMap<String, Object> params){
        int pageIndex = Integer.valueOf(params.get("page").toString()) -1;
        int pageSize = Integer.valueOf(params.get("limit").toString());
        params.put("page", pageSize * pageIndex);
        params.put("limit", Integer.valueOf(params.get("limit").toString()));
        return partMapper.querySchoolByNodeId(params);
    }
    public int querySchoolByConditionTotal(HashMap<String, Object> params){
        return partMapper.querySchoolByNodeIdTotal(params);
    }
//--------------------------------------分页查询节点园区数据 end------------------------------------------

    public  Integer queryByNodeId(Integer id){
        return  partMapper.queryByNodeId(id);
    }

    public  List<HashMap> schoolUser(){
        return  partMapper.schoolUser();
    }

    /**
     * 查询费用
     * @param partId
     * @return
     */
    public Integer queryCost(Long partId){
        return partMapper.queryCost(partId);
    }

    /**
     * 查询有效期
     * @param partId
     * @return
     */
    public Integer queryValidityTime(Long partId){
        return partMapper.queryValidityTime(partId);
    }


    //--------------------------------分页查询节点园区数据begin------------------------------------------
    public List<HashMap> pageQuerySchoolByNodeId(HashMap<String, Object> params){
        int pageIndex = Integer.valueOf(params.get("page").toString()) -1;
        int pageSize = Integer.valueOf(params.get("limit").toString());
        params.put("page", pageSize * pageIndex);
        params.put("limit", Integer.valueOf(params.get("limit").toString()));
        if(params.get("level").toString().equals("1")){//第1级区域节点

            params.put("id", params.get("areaId").toString().substring(0,2));
        }
        if(params.get("level").toString().equals("2")){//第2级区域节点
            params.put("id", params.get("areaId").toString().substring(0,4));
        }
        if(params.get("level").toString().equals("3")){//第3级区域节点
            params.put("id", params.get("areaId").toString());
        }


        return partMapper.pageQuerySchoolByNodeId(params);
    }
    public int pageQuerySchoolByNodeIdTotal(HashMap<String, Object> params){
        if(params.get("level").toString().equals("1")){//第1级区域节点

            params.put("id", params.get("areaId").toString().substring(0,2));
        }
        if(params.get("level").toString().equals("2")){//第2级区域节点
            params.put("id", params.get("areaId").toString().substring(0,4));
        }
        if(params.get("level").toString().equals("3")){//第3级区域节点
            params.put("id", params.get("areaId").toString());
        }
        return partMapper.pageQuerySchoolByNodeIdTotal(params);
    }
//--------------------------------------分页查询节点园区数据 end------------------------------------------

    /**
     * 根据学校ID查询讯学校数据
     * @param id
     * @return
     */
    public HashMap querySchoolBySchoolId(Long id) {
        return mapper.querySchoolBySchoolId(id);
    }

    /**
     * 根据用户id查询学校ID
     * @param id
     * @return
     */
    public Long selectSchoolIdByUserId(Long id) {
        return mapper.selectSchoolIdByUserId(id);
    }


}
