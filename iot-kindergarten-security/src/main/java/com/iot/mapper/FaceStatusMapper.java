package com.iot.mapper;

import com.iot.entity.FaceStatus;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;


public interface FaceStatusMapper extends Mapper<FaceStatus> {

    int insertByPersonArray(@Param("deviceId")Long deviceId, @Param("array") Long[] array);

    int updateByPersonArray(@Param("deviceId")Long deviceId, @Param("array") Long[] array);

    int deleteByPersonArray(@Param("deviceId")Long deviceId, @Param("array") Long[] array);

    int updateStatusByPersonId(@Param("personId") Long personId, @Param("status")Integer status);

    Long[] selectNeedUpdateStudentByDeviceId(Long deviceId);

    Long[] selectNeedUpdateParentByDeviceId(Long deviceId);

    int updateStatusByDeviceId(@Param("deviceId") Long deviceId, @Param("status")Integer status);
}
