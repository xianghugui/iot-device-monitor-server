package com.iot.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/*
 *
 * 功能描述: 设备关联园区
 * @author FQ
 * @date 11/9/2018 3:28 PM
 */
@Table(name = "t_part_device")
public class PartDevice {
    @Id
    private Long id;

    @Column(name = "part_id")
    private Long partId;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "name")
    private String name;

    @Column(name = "create_time")
    private Date createTime;

    // 0, 关闭  1, 正常
    @Column(name = "status")
    private boolean status;

    private String partName;

    private String code;

    private Integer type;

    private Integer parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public interface Property {
        String id= "id";
        String partId= "partId";
        String deviceId= "deviceId";
        String name= "name";
        String createTime= "createTime";
        String status= "status";
        Integer Enable = 1;
        Integer Disable = 0;
    }
}
