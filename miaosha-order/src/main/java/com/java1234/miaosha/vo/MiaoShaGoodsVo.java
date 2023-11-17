package com.java1234.miaosha.vo;


import com.java1234.miaosha.entity.MiaoShaGoods;
import lombok.Data;

/**
 * @author zk
 * @company Java知识分享网
 * @create 2020-12-17 19:28
 */
@Data
public class MiaoShaGoodsVo extends MiaoShaGoods {

    private Integer miaoShaStatus=0; // 秒杀状态
    private Integer remainBeginSecond=0; // 剩余多少秒 秒杀开始
    private Integer remainEndSecond=0; // 秒杀结束 剩余多少秒
}
