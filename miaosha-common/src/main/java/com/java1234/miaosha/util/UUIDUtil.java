package com.java1234.miaosha.util;

import java.util.UUID;

/**
 * uuid工具类
 * @author zk
 * @company Java知识分享网
 * @create 2020-11-28 14:00
 */
public class UUIDUtil {

    public static String genUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtil.genUuid());
    }

}
