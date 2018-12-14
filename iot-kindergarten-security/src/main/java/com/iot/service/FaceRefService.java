package com.iot.service;

import com.iot.entity.FaceRef;
import com.iot.mapper.FaceRefMapper;
import com.iot.security.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class FaceRefService extends BaseBiz<FaceRefMapper, FaceRef> {
    @Autowired
    public FaceRefMapper faceRefMapper;

    @Override
    public void insertSelective(FaceRef faceRef) {
        faceRef.setId(System.nanoTime());
        super.insertSelective(faceRef);
    }

    public String[] queryByPersonId(Long personId){
        return faceRefMapper.queryByPersonId(personId);
    }

    public void deleteByPersonId(Long personId){
        faceRefMapper.deleteByPersonId(personId);
    }
}
