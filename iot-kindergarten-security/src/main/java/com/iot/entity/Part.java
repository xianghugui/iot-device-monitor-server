package com.iot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Getter
@Setter
@Table(name="t_part")
public class Part {
    @Id
    private Long id;
    //校园
    private String code;
    // 地区ID
    @Column(name = "parent_id")
    private Integer parentId;
    // 详细地址
    private String address;
    // 园区名字
    @Column(name = "part_name")
    private String partName;
    // 园区负责人
    @Column(name = "part_manager")
    private Long partManager;

    @Column(name = "create_time")
    private Date createTime;
    @Transient//忽略对象字段持久化注解
    private  Integer [] partNode;//省市区
}
