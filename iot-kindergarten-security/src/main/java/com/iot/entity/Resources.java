package com.iot.entity;

import com.iot.entity.hsweb.file.FileUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 资源类实体
 */
@Getter
@Setter
@Table(name = "t_resources")
public class Resources{
    @Id
    private Long id;
    //资源文件名
    private String name;
    //资源文件名
    private String path;
    //资源类型
    private String type;
    //md5码
    private String md5;
    //资源文件名
    private Long size;
    //资源创建者id
    @Column(name = "creator_id")
    private Long createId;
    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    public String getSuffix() {
        return FileUtils.getSuffix(getName());
    }


}
