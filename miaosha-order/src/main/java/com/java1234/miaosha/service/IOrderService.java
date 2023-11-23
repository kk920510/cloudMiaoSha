package com.java1234.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java1234.miaosha.entity.Order;

import java.util.Map;

/**
 * 用户Service接口
 * @author zk
 * @create 2023-11-13 21:36
 */
public interface IOrderService extends IService<Order> {

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    public Order findById(String id);

    /**
     * 根据用户id和秒杀商品id查询订单
     * @param map
     * @return
     */
    public Order findByUserIdAndMiaoShaGoodsId(Map map);

    /**
     * 查询秒杀结果
     * @param userId
     * @param miaoShaGoodsId
     * @return
     */
    public String getMiaoShaResult(Integer userId, Integer miaoShaGoodsId);
}
