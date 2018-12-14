package com.iot.controller;

import com.iot.entity.Area;
import com.iot.security.common.msg.ObjectRestResponse;
import com.iot.security.common.rest.BaseController;
import com.iot.service.AreaService;
import com.iot.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/*
 *
 * 功能描述: Student 控制层
 * @author FQ
 * @date 11/7/2018 2:05 PM
 *
 */
@RestController
@RequestMapping("area")
public class AreaController extends BaseController<AreaService, Area> {


    @Autowired
    private AreaService areaService;

    @Autowired
    private PartService partService;

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse updateArea(@RequestBody Area area) {
        areaService.updateStatusByAreaId(area.getNodeId());//  更新区状态
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/updateToZero/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse updateToZero(@PathVariable("id") Integer id) {
        if(partService.queryByNodeId(id) > 0){//当前节点下存在园区数据
            return new ObjectRestResponse().rel(false);
        }
        areaService.updateDeleteStatusByAreaId(id);
        return new ObjectRestResponse().rel(true);
    }
}
