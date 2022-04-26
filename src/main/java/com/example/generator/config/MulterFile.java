package com.example.generator.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 上传文件大小配置
 * @author wangjing
 */
@Configuration
public class MulterFile {
    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        //factory.setMaxFileSize("30960KB");
        /// 设置总上传数据总大小
        //factory.setMaxRequestSize("3096000KB");
        return factory.createMultipartConfig();
    }
}