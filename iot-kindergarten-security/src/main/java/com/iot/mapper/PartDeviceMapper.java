package com.iot.mapper;

import com.iot.entity.PartDevice;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface PartDeviceMapper extends Mapper<PartDevice> {
    List<PartDevice> queryPage(Map<String, Object> params);
    Integer queryPageTotal(Map<String, Object> params);
    void deleteByDeviceId(Long deviceId);
    Long queryDeviceIdById(Long id);
    String querySchoolNameByDeviceId(Long deviceId);
    Map<String,Long> queryIdByPartId(Long partId);
}
