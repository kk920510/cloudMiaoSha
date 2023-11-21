package com.java1234.miaosha.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀商品实体
 * @author zk
 * @create 2023-11-17 10:57
 */
@TableName("t_goods_miaosha")
@Data
public class MiaoShaGoods implements Serializable {

    private Integer id; // 编号

    @TableField(select = false)
    private Goods goods; // 关联商品

    private double price; // 秒杀价格

    private Integer stock; // 库存数量

    @JsonSerialize(using= CustomDateTimeSerializer.class)
    private Date startTime; // 秒杀开始时间

    @JsonSerialize(using= CustomDateTimeSerializer.class)
    private Date endTime; // 秒杀结束时间




}
