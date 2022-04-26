package com.example.generator.model;

import lombok.Data;

@Data
public class WriteLog {

    private Integer id;
    private String ip;

    private String url;
    private String method;
    private String methodName;
    private String remark;


}
