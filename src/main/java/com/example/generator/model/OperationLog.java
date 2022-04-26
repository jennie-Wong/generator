package com.example.generator.model;

import lombok.Data;

import java.util.Date;

@Data
public class OperationLog {
    private Integer id;

    private String ip;

    private String url;

    private String userName;

    private String method;

    private String methodName;

    private String remark;

    private Date createTime;


}