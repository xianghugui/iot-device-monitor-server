package com.iot.security.auth.controller;

import com.iot.security.auth.biz.ClientBiz;
import com.iot.security.auth.entity.Client;
import com.iot.security.auth.entity.ClientService;
import com.iot.security.common.msg.ObjectRestResponse;
import com.iot.security.common.rest.BaseController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("service")
public class ServiceController extends BaseController<ClientBiz,Client>{

    @RequestMapping(value = "/{id}/client", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse modifyUsers(@PathVariable int id, String clients){
        baseBiz.modifyClientServices(id, clients);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/client", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<ClientService> getUsers(@PathVariable int id){
        return new ObjectRestResponse<ClientService>().rel(true).data(baseBiz.getClientServices(id));
    }
}
