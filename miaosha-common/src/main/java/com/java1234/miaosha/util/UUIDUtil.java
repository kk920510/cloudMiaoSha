package com.java1234.miaosha.util;

import java.util.UUID;

/**
 * uuid工具类
 * @author zk
 * @create 2023-11-18 14:00
 */
public class UUIDUtil {

    public static String genUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtil.genUuid());
    }

}
