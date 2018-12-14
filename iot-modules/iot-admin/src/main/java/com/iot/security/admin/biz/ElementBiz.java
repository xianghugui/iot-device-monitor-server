package com.iot.security.admin.biz;

import com.iot.cache.annotation.Cache;
import com.iot.cache.annotation.CacheClear;
import com.iot.security.admin.entity.Element;
import com.iot.security.admin.mapper.ElementMapper;
import com.iot.security.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ElementBiz extends BaseBiz<ElementMapper,Element> {
    @Cache(key="permission:ele:u{1}")
    public List<Element> getAuthorityElementByUserId(String userId){
       return mapper.selectAuthorityElementByUserId(userId);
    }
    public List<Element> getAuthorityElementByUserId(String userId,String menuId){
        return mapper.selectAuthorityMenuElementByUserId(userId,menuId);
    }

    @Cache(key="permission:ele")
    public List<Element> getAllElementPermissions(){
        return mapper.selectAllElementPermissions();
    }

    @Override
    @CacheClear(keys={"permission:ele","permission"})
    public void insertSelective(Element entity) {
        super.insertSelective(entity);
    }

    @Override
    @CacheClear(keys={"permission:ele","permission"})
    public void updateSelectiveById(Element entity) {
        super.updateSelectiveById(entity);
    }
}
