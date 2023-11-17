package com.java1234.miaosha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java1234.miaosha.entity.Order;

import java.util.Map;

/**
 * 用户Mapper接口
 * @author zk
 * @company Java知识分享网
 * @create 2021-01-23 21:34
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    public Order findById(String id);

    /**
     * 订单生成
     * @param order
     * @return
     */
    public Integer add(Order order);

    /**
     * 根据用户id和秒杀商品id查询订单
     * @param map
     * @return
     */
    public Order findByUserIdAndMiaoShaGoodsId(Map map);
}
