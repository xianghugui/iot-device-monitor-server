package com.iot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Getter
@Setter
@Table(name = "t_user")
public class User {
    @Id
    private Long id;
    // 联系人名称
    private String name;
    // 联系方式
    private String phone;
    // 接送人头像地址
    private Long photo;
    //创建时间
    @Column(name = "create_time")
    private Date createTime;
    //（0 男， 1 女）
    private Integer sex;
    //状态（0 正常，1 删除）
    private Integer status;
}
