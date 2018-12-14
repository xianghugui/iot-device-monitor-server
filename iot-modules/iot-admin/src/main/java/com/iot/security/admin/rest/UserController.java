package com.iot.security.admin.rest;

import com.iot.security.admin.biz.MenuBiz;
import com.iot.security.admin.biz.UserBiz;
import com.iot.security.admin.entity.Menu;
import com.iot.security.admin.entity.User;
import com.iot.security.admin.rpc.service.PermissionService;
import com.iot.security.admin.vo.FrontUser;
import com.iot.security.admin.vo.MenuTree;
import com.iot.security.common.msg.ObjectRestResponse;
import com.iot.security.common.msg.TableResultResponse;
import com.iot.security.common.rest.BaseController;
import com.iot.security.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserBiz,User> {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuBiz menuBiz;
    @Autowired
    private UserBiz userBiz;


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse addUser(@RequestBody User entity){
        if(userBiz.getUserByUsername(entity.getUsername()) == null){//当前用户不存在
            baseBiz.insertSelective(entity);
            return new ObjectRestResponse<>().data(200);
        }
        return new ObjectRestResponse<>().data(201);//当前用户已存在
    }

    /**
     * 分页查询用户数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/listUser", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<HashMap> pageList(@RequestParam HashMap<String, Object> params) {
        List<HashMap> partSchoolList = baseBiz.queryUserByUserName(params);
        int total = baseBiz.queryUserByUserNameTotal(params);
        return new TableResultResponse<>(total,partSchoolList);
    }

    @RequestMapping(value = "/front/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfo(String token) throws Exception {
        FrontUser userInfo = permissionService.getUserInfo(token);
        if(userInfo==null) {
            return ResponseEntity.status(401).body(false);
        } else {
            return ResponseEntity.ok(userInfo);
        }
    }

    @RequestMapping(value = "/front/menus", method = RequestMethod.GET)
    public @ResponseBody
    List<MenuTree> getMenusByUsername(String token) throws Exception {
        return permissionService.getMenusByUsername(token);
    }

    @RequestMapping(value = "/front/menu/all", method = RequestMethod.GET)
    public @ResponseBody
    List<Menu> getAllMenus() throws Exception {
        return menuBiz.selectListAll();
    }
}
