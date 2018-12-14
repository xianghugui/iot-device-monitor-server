package com.iot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Getter
@Setter
@Table(name="t_area")
public class Area {
    @Id
    private Integer id;
    @Column(name="area_name")
    private String partName;
    @Column(name="parent_id")
    private  Integer parentId;

    private  Integer status;//区域状态（1标识当前节点下有数据，0 标识没有）

    private  Integer level;//节点等级
    @Transient//忽略对象字段持久化注解
    private  Integer [] nodeId;//省市区id
}
