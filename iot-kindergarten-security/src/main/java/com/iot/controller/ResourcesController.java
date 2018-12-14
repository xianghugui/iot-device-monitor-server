package com.iot.controller;

import com.iot.entity.Resources;
import com.iot.security.common.msg.ObjectRestResponse;
import com.iot.security.common.rest.BaseController;
import com.iot.service.ResourcesService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("resources")
public class ResourcesController extends BaseController<ResourcesService, Resources> {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ResourcesService resourcesService;

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public ObjectRestResponse downLoad(@PathVariable("id") String id,
                                       @RequestParam(value = "name", required = false) String name,
                                       HttpServletResponse response, HttpServletRequest request) throws Exception {
       return resourcesService.download(id,name,response,request);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ObjectRestResponse upload(@RequestParam("file") MultipartFile[] files) throws IOException {
        return new ObjectRestResponse().data(resourcesService.upload(files)).rel(true);
    }
}
