package com.iot.service;

import com.alibaba.fastjson.JSONArray;
import com.iot.entity.Device;
import com.iot.mapper.AreaMapper;
import com.iot.mapper.DeviceMapper;
import com.iot.mapper.PartDeviceMapper;
import com.iot.security.common.biz.BaseBiz;
import com.iot.security.common.msg.TableResultResponse;
import com.iot.util.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.iot.util.TreeUtil.listToTree;


@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceService extends BaseBiz<DeviceMapper, Device> {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private PartDeviceMapper partDeviceMapper;

    /*
     * 功能描述: 新增设备
     *
     */
    @Override
    public void insertSelective(Device device) {
        device.setCreateTime(new Date());
        device.setStatus(0);
        device.setId(System.nanoTime());
        super.insertSelective(device);
    }

    /*
     * 功能描述: 修改设备
     *
     */
    @Override
    public void updateSelectiveById(Device device) {
        super.updateSelectiveById(device);
    }

    /*
     * 功能描述: 删除设备
     *
     */
    public void deleteById(Long id) {
        partDeviceMapper.deleteByDeviceId(id);
        mapper.updateStatusById(id,2);
    }

    /**
     * 分页查询节点下所有设备
     * @param
     * @return
     */
    public TableResultResponse queryPage(QueryParams params){
        int total = deviceMapper.queryPageTotal(params);
        List<Map> result = deviceMapper.queryPage(params);
        return new TableResultResponse(total,result);
    }

    /**
     * 构建省市区组织   四级树
     * @return
     */
    public JSONArray areaOrganizationList() {
        JSONArray jsonArrayList = areaMapper.areaSchoolList();//有组织的省市区数据
        JSONArray jsonArrayClassList = mapper.queryAllOrganization();//所有的组织数据
        JSONArray jsonArray = new JSONArray();//新建一个jsonarray
        jsonArray.addAll(jsonArrayList);//添加所有区域数据
        jsonArray.addAll(jsonArrayClassList);//添加所有学校数据
        JSONArray result = listToTree(jsonArray,"id","parentId","children");
        return result;
    }


    //--------------------------------分页查询节点 设备数据begin------------------------------------------

    public List<HashMap> queryDeviceByCondition(HashMap<String, Object> params){
        int pageIndex = Integer.valueOf(params.get("page").toString()) -1;
        int pageSize = Integer.valueOf(params.get("limit").toString());
        params.put("page", pageSize * pageIndex);
        params.put("limit", Integer.valueOf(params.get("limit").toString()));
        if(params.get("level").toString().equals("1")){//第1级区域节点

            params.put("id", params.get("nodeId").toString().substring(0,2));
        }
        if(params.get("level").toString().equals("2")){//第2级区域节点
            params.put("id", params.get("nodeId").toString().substring(0,4));
        }
        if(params.get("level").toString().equals("3")){//第3级区域节点
            params.put("id", params.get("nodeId").toString());
        }
        if(params.get("level").toString().equals("4") ){//第4级区域节点
            params.put("id", params.get("nodeId").toString());
        }
        return deviceMapper.queryDeviceByNodeId(params);
    }
    public int queryDeviceByConditionTotal(HashMap<String, Object> params){
        if(params.get("level").toString().equals("1")){//第1级区域节点

            params.put("id", params.get("nodeId").toString().substring(0,2));
        }
        if(params.get("level").toString().equals("2")){//第2级区域节点
            params.put("id", params.get("nodeId").toString().substring(0,4));
        }
        if(params.get("level").toString().equals("3")){//第3级区域节点
            params.put("id", params.get("nodeId").toString());
        }
        if(params.get("level").toString().equals("4")){//第4级区域节点
            params.put("id", params.get("nodeId").toString());
        }
        return deviceMapper.queryDeviceByNodeIdTotal(params);
    }
//--------------------------------------分页查询节点 设备数据 end------------------------------------------
}
