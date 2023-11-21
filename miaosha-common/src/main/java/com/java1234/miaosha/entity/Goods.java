package com.java1234.miaosha.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品实体
 * @author zk
 * @create 2023-11-17 10:57
 */
@TableName("t_goods")
@Data
public class Goods implements Serializable {

    private Integer id; // 编号

    private String name; // 名称

    private double price; // 价格

    private String image; // 商品图片

    private int stock; // 商品库存

    private String detail; // 商品详情




}
