package com.iot.mapper;

import com.alibaba.fastjson.JSONArray;
import com.iot.entity.Device;
import com.iot.util.QueryParams;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DeviceMapper extends Mapper<Device> {

    void updateStatusById(@Param("id") Long id,@Param("status") int status);

    List<Map> queryPage(QueryParams params);

    int queryPageTotal(QueryParams params);

    /**
     * 查询所有的组织
     * @return
     */
    JSONArray queryAllOrganization();

    /**
     * 根据条件分页查询设备信息
     * @param params 分页信息  包括查询条件
     * @return
     */
    List<HashMap> queryDeviceByNodeId (HashMap<String, Object> params);

    /**
     * 根据条件分页查询设备信息总数
     * @param params
     * @return
     */
    Integer queryDeviceByNodeIdTotal(HashMap<String, Object> params);
}
