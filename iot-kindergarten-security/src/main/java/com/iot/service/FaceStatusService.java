package com.iot.service;

import com.iot.entity.FaceStatus;
import com.iot.mapper.FaceStatusMapper;
import com.iot.security.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

/**
 * t_face_status表中没有对应的记录说明没有注册到设备
 * face_status字段为1说明对应的设备，人脸信息已注册
 * face_status字段为2说明对应的设备，人脸信息需要修改
 * face_status字段为3说明对应的设备，人脸信息需要删除
 */
@Service
public class FaceStatusService extends BaseBiz<FaceStatusMapper, FaceStatus> {

    /**
     * 根据学生/家长ID更新状态
     * @param personId
     * @param status
     * @return
     */
    public int updateStatusByPersonId(Long personId, Integer status) {
        return mapper.updateStatusByPersonId(personId, status);
    }

    /**
     * 根据学生/家长ID更新状态
     * @param deviceId
     * @param status
     * @return
     */
    public int updateStatusByDeviceId(Long deviceId, Integer status) {
        return mapper.updateStatusByDeviceId(deviceId, status);
    }

    /**
     * 根据学生/家长ID 数组插入记录为已注册
     * @param deviceId
     * @param array
     * @return
     */
    public int insertByPersonArray(Long deviceId, Long[] array) {
        return mapper.insertByPersonArray(deviceId, array);
    }

    /**
     * 根据学生/家长ID 数组将待修改改为已注册
     * @param deviceId
     * @param array
     * @return
     */
    public int updateByPersonArray(Long deviceId, Long[] array) {
        return mapper.updateByPersonArray(deviceId, array);
    }

    /**
     * 根据学生/家长ID 数组将状态待删除的数据删除
     * @param deviceId
     * @param array
     * @return
     */
    public int deleteByPersonArray(Long deviceId, Long[] array) {
        return mapper.deleteByPersonArray(deviceId, array);
    }

    /**
     * 根据设备ID查询需要更新的学生人脸信息
     * @param deviceId
     * @return
     */
    public Long[] selectNeedUpdateStudentByDeviceId(Long deviceId){
        return mapper.selectNeedUpdateStudentByDeviceId(deviceId);
    }

    /**
     * 根据设备ID查询需要更新的家长人脸信息
     * @param deviceId
     * @return
     */
    public Long[] selectNeedUpdateParentByDeviceId(Long deviceId){
        return mapper.selectNeedUpdateParentByDeviceId(deviceId);
    }

}
