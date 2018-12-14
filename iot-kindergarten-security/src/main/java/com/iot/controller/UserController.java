package com.iot.controller;

import com.iot.entity.User;
import com.iot.security.common.msg.ObjectRestResponse;
import com.iot.security.common.msg.TableResultResponse;
import com.iot.security.common.rest.BaseController;
import com.iot.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserService, User> {


//
//    @RequestMapping(value = "/update", method = RequestMethod.PUT)
//    @ResponseBody
//    public ObjectRestResponse updateParent(@RequestBody User user) {
//        baseBiz.updateSelectiveById(user);
//        return new ObjectRestResponse().rel(true);
//    }
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public ObjectRestResponse deletePart(@PathVariable("id") Long id) {
//        baseBiz.deleteById(id);
//        return new ObjectRestResponse().rel(true);
//    }
////    @RequestMapping(value = "/list", method = RequestMethod.GET)
////    @ResponseBody
////    public TableResultResponse<> page(@RequestParam HashMap<String, Object> params) {
////
////        int total = baseBiz.queryStudentByConditionTotal(params);
////        return new TableResultResponse<>(total,);
////    }
//
//    @GetMapping("/unallocated")
//    public ObjectRestResponse listUnallocated() {
//        return new ObjectRestResponse<>().data(baseBiz.listUnallocated()).rel(true);
//    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ObjectRestResponse addParent(@RequestBody User user) {
        baseBiz.insertSelective(user);
        return new ObjectRestResponse().rel(true);
    }
    /**
     * 分页查询所有用户数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<HashMap> page(@RequestParam HashMap<String, Object> params) {
        List<HashMap> partSchoolList = baseBiz.pageQueryUser(params);
        Long total = baseBiz.pageQueryUserTotal(params);
        return new TableResultResponse<>(total,partSchoolList);
    }
}
