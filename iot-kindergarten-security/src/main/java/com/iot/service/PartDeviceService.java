package com.iot.service;

import com.iot.entity.PartDevice;
import com.iot.mapper.DeviceMapper;
import com.iot.mapper.PartDeviceMapper;
import com.iot.security.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class PartDeviceService extends BaseBiz<PartDeviceMapper, PartDevice> {

    @Autowired
    private DeviceMapper deviceMapper;

    public Map<String,Long> queryIdByPartId(Long partId){
        return mapper.queryIdByPartId(partId);
    }

    /*
     * 功能描述: 新增关联
     *
     */
    @Override
    @Transactional
    public void insertSelective(PartDevice partDevice) {
        partDevice.setId(System.nanoTime());
        partDevice.setCreateTime(new Date());
        partDevice.setStatus(true);
        deviceMapper.updateStatusById(partDevice.getDeviceId(), 1);
        super.insertSelective(partDevice);
    }

    /*
     * 功能描述: 修改关联
     *
     */
    @Override
    @Transactional
    public void updateSelectiveById(PartDevice partDevice) {
        Long oldDeviceId = mapper.queryDeviceIdById(partDevice.getId());
        if (partDevice.getDeviceId() != null && !oldDeviceId.equals(partDevice.getDeviceId())) {
            deviceMapper.updateStatusById(oldDeviceId, 0);
            deviceMapper.updateStatusById(partDevice.getDeviceId(), 1);
        }
        super.updateSelectiveById(partDevice);
    }

    /**
     * 功能描述: 分页查询
     *
     * @param params
     * @return
     */
    public List<PartDevice> queryPage(Map<String, Object> params) {
        int pageIndex = Integer.valueOf(params.get("page").toString()) -1;
        int pageSize = Integer.valueOf(params.get("limit").toString());
        params.put("page", pageSize * pageIndex);
        params.put("limit", Integer.valueOf(params.get("limit").toString()));
        return mapper.queryPage(params);
    }

    /**
     * 功能描述: 查询全部数据总条数
     *
     * @param params
     * @return
     */
    public int queryTotal(Map<String, Object> params) {
        return mapper.queryPageTotal(params);
    }

    /**
     * 根据设备ID查询学校ID
     * @param deviceId
     * @return
     */
    public List<PartDevice> selectByDeviceId(Long deviceId) {
        Example example = Example.builder(PartDevice.class).distinct()
                .select(PartDevice.Property.partId)
                .where(Sqls.custom().andEqualTo(PartDevice.Property.deviceId, deviceId)
                        .andEqualTo(PartDevice.Property.status, PartDevice.Property.Enable))
                .build();
        return mapper.selectByExample(example);
    }

    public String querySchoolNameByDeviceId(Long deviceId) {
        return mapper.querySchoolNameByDeviceId(deviceId);
    }
}
