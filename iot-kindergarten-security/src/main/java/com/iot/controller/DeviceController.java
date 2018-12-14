package com.iot.controller;

import com.iot.entity.Device;
import com.iot.util.QueryParams;
import com.iot.security.common.msg.TableResultResponse;
import com.iot.service.DeviceService;
import com.iot.security.common.msg.ObjectRestResponse;
import com.iot.security.common.rest.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *
 * 功能描述: Device 控制层
 * @author FQ
 * @date 11/7/2018 2:05 PM
 *
 */
@Controller
@RequestMapping("device")
public class DeviceController extends BaseController<DeviceService, Device> {
    @Autowired
    private DeviceService deviceService;

    /**
     *  新增
     * @param device
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse addDevice(@RequestBody Device device) {
        device.setCreateTime(new Date());
        baseBiz.insertSelective(device);
        return new ObjectRestResponse().rel(true);
    }

    /**
     *  编辑
     * @param device
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse updateDevice(@RequestBody Device device) {
        baseBiz.updateSelectiveById(device);
        return new ObjectRestResponse().rel(true);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse deletePart(@PathVariable("id") Long id) {
        baseBiz.deleteById(id);
        return new ObjectRestResponse().rel(true);
    }

    /**
     * 分页查询设备
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse page(@RequestParam Map<String, Object> map) {
        QueryParams params = new QueryParams(map);
        return deviceService.queryPage(params);
    }

    @RequestMapping(value = "/areaOrganizationList", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse areaSchoolList() {
        return new ObjectRestResponse<>().data(deviceService.areaOrganizationList());
    }

    @RequestMapping(value = "/pageDevice", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<HashMap> pageSchool(@RequestParam HashMap<String, Object> params) {
        List<HashMap> partSchoolList = baseBiz.queryDeviceByCondition(params);
        int total = baseBiz.queryDeviceByConditionTotal(params);
        return new TableResultResponse<>(total,partSchoolList);
    }
}
