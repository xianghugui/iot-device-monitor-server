package com.iot.controller;

import com.iot.entity.*;
import com.iot.security.common.msg.ObjectRestResponse;
import com.iot.security.common.msg.TableResultResponse;
import com.iot.security.common.rest.BaseController;
import com.iot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("part")
public class PartController extends BaseController<PartService, Part> {
    @Autowired
    private AreaService areaService;
    @Autowired
    private PartService partService;
    /**
     * 新增组织节点
     *
     * @param part
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ObjectRestResponse addPart(@RequestBody Part part) {
        if(partService.queryHasCode(part.getCode()) == null){
            areaService.updateStatusByAreaId(part.getPartNode());//  更新区状态
            baseBiz.insertSelective(part);
            return new ObjectRestResponse().rel(true);
        }
        return new ObjectRestResponse().data("组织编码已存在").rel(false);
    }

    /**
     * 编辑园区
     *
     * @param part
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse updatePart(@RequestBody Part part) {
        Part queryPart = partService.selectById(part.getId());
        if((queryPart.getCode() != null && queryPart.getCode().equals(part.getCode())) || partService.queryHasCode(part.getCode()) == null) {
            areaService.updateStatusByAreaId(part.getPartNode());//  更新区状态
            baseBiz.updateSelectiveById(part);
            return new ObjectRestResponse().rel(true);
        }
        return new ObjectRestResponse().data("校园编号已存在").rel(false);
    }

    /**
     * 全国省市区树查询
     * @return
     */
    @RequestMapping(value = "/createAreaTree", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse createAreaTree() {
        return new ObjectRestResponse<>().data(areaService.queryAllAreaTreeNode());
    }

    /**
     * 查询有学校的所有省市区节点数据
     * @return
     */
    @RequestMapping(value = "/areaSchoolList", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse areaSchoolList() {
        return new ObjectRestResponse<>().data(areaService.areaSchoolList());
    }

    /**
     * 分页查询所有学校数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<HashMap> page(@RequestParam HashMap<String, Object> params) {
        List<HashMap> partSchoolList = baseBiz.querySchoolByCondition(params);
        int total = baseBiz.querySchoolByConditionTotal(params);
        return new TableResultResponse<>(total,partSchoolList);
    }

    /**
     * 查询为关联组织的负责人
     * @return
     */
    @RequestMapping(value = "/schoolUser", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse schoolUser() {
        return new ObjectRestResponse<>().data(baseBiz.schoolUser());
    }

    /**
     * 分页查询当前节点所有学校数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/pageSchool", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<HashMap> pageSchool(@RequestParam HashMap<String, Object> params) {
        List<HashMap> partSchoolList = baseBiz.pageQuerySchoolByNodeId(params);
        int total = baseBiz.pageQuerySchoolByNodeIdTotal(params);
        return new TableResultResponse<>(total,partSchoolList);
    }

    @Override
    @GetMapping("/{id}")
    public ObjectRestResponse get(@PathVariable Long id){
        return new ObjectRestResponse<HashMap>().data(baseBiz.querySchoolBySchoolId(id)).rel(true);
    }

}
