package com.example.generator.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysUserReq {
    private Integer id;

    private String sex;

    private String phone;

    private String address;

    private String sign;

    private String name;

    private String pwd;

    private String remark;

    private String remark1;

    private String remark2;

    private String remark3;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    private byte[] photo;

    private String oldPwd;

}