package com.iot.mapper;

import com.iot.entity.FaceRef;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FaceRefMapper extends Mapper<FaceRef> {
    /**
     * 根据宝宝id/接送人id查询资源id
     * @param personId
     * @return
     */
    String[] queryByPersonId(Long personId);

    /**
     * 根据宝宝id/接送人id删除资源id
     * @param personId
     * @return
     */
    void deleteByPersonId(Long personId);
}
