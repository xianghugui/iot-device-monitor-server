package com.iot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/*
 *
 * 功能描述: 设备
 * @author FQ
 * @date 11/7/2018 1:37 PM
 *
 */
@Getter
@Setter
@Table(name="t_device")
public class Device {
    @Id
    private Long id;
    // 设备编号
    private String code;

    // 组织ID
    @Column(name = "parent_id")
    private Long parentId;

    private String name;

    // 设备类型: 0, 晨检设备 1, 考勤设备
    private Integer type;
    // 设备状态（0 未关联，1 已关联, 2 已删除）
    private int status;
    // 创建时间
    @Column(name="create_time")
    private Date createTime;
    // 备注
    private String note;
}
