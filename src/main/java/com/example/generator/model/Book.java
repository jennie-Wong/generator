package com.example.generator.model;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    private Integer id;

    private Integer userId;

    private String bookName;

    private byte[] page;

    private String url;

    private String isPublic;

    private String remark;

    private String remark1;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;


}