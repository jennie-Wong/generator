package com.example.generator.model;

import lombok.Data;

import java.util.Date;

@Data
public class BookDetail {
    private Integer id;

    private String detailName;

    private Integer bookId;

    private String url;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    private byte[] content;

}