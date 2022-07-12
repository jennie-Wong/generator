package com.example.generator.proxy.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author wangjing
 */
@Component
@Slf4j
public class CityInterfaceImpl {
    public String getCity(String  city) {
        log.info("====城市:{}=====",city);
        return city;
    }
}
