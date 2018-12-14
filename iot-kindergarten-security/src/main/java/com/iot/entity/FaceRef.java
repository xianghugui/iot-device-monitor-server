package com.iot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
@Getter
@Setter
@Table(name = "t_face_ref")
public class FaceRef {
    @Id
    private Long id;
    @Column(name = "person_id")
    private Long personId;
    @Column(name = "resource_id")
    private Long resourceId;
    private Integer type;
}
