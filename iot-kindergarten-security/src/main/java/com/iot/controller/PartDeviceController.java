package com.iot.controller;

import com.iot.entity.*;
import com.iot.security.common.msg.ObjectRestResponse;
import com.iot.security.common.msg.TableResultResponse;
import com.iot.security.common.rest.BaseController;
import com.iot.service.DeviceService;
import com.iot.service.PartDeviceService;
import com.iot.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *
 * 功能描述: Part 控制层
 * @author FQ
 * @date 11/7/2018 2:05 PM
 *
 */
@Controller
@RequestMapping("partDevice")
public class PartDeviceController extends BaseController<PartDeviceService, PartDevice> {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private PartService partService;

    /**
     * 新增园区
     *
     * @param partDevice
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse addPart(@RequestBody PartDevice partDevice) {
        baseBiz.insertSelective(partDevice);
        return new ObjectRestResponse().rel(true);
    }

    /**
     * 编辑园区
     *
     * @param partDevice
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse updatePart(@RequestBody PartDevice partDevice) {
        baseBiz.updateSelectiveById(partDevice);
        return new ObjectRestResponse().rel(true);
    }

    /**
     * 分页查询设备
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<PartDevice> page(@RequestParam Map<String, Object> params) {
        List<PartDevice> partDeviceList = baseBiz.queryPage(params);
        int total = baseBiz.queryTotal(params);
        return new TableResultResponse<>(total,partDeviceList);
    }
}
