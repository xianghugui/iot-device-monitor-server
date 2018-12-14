package com.iot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
@Getter
@Setter
@Table(name="t_face_status")
public class FaceStatus {
    @Id
    private Long personId;
    @Id
    private Long deviceId;
    //0待注册1已注册2待修改3待删除
    private Integer faceStatus;
}
