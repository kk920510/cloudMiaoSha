package com.java1234.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java1234.miaosha.entity.Order;
import com.java1234.miaosha.mapper.OrderMapper;
import com.java1234.miaosha.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户Service实现类
 * @author zk
 * @create 2023-11-23 21:37
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public Order findById(String id) {
        return orderMapper.findById(id);
    }

    @Override
    public Order findByUserIdAndMiaoShaGoodsId(Map map) {
        return orderMapper.findByUserIdAndMiaoShaGoodsId(map);
    }
}
