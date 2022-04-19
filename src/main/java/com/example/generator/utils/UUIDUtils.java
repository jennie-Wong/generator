package com.example.generator.utils;

import java.util.UUID;

/**
 * @Title: UUIDUtils
 * @Description: UUIDUtils
 * @Author: WangDongsheng
 * @Date: 2020-04-27 14:14
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
