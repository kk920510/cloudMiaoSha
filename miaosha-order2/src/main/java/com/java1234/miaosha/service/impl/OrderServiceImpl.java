package com.java1234.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java1234.miaosha.constant.Constant;
import com.java1234.miaosha.entity.Order;
import com.java1234.miaosha.mapper.OrderMapper;
import com.java1234.miaosha.service.IOrderService;
import com.java1234.miaosha.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户Service实现类
 * @author zk
 * @create 2023-11-23 21:37
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public Order findById(String id) {
        return orderMapper.findById(id);
    }

    @Override
    public Order findByUserIdAndMiaoShaGoodsId(Map map) {
        return orderMapper.findByUserIdAndMiaoShaGoodsId(map);
    }

    @Override
    public String getMiaoShaResult(Integer userId, Integer miaoShaGoodsId) {
        HashMap map=new HashMap();
        map.put("user_id",userId);
        map.put("miaosha_goods_id",miaoShaGoodsId);
        Order order=orderMapper.findByUserIdAndMiaoShaGoodsId(map);
        if(order!=null){
            return order.getId();
        }else{
            // TODO 查询商品是否秒杀完 从redis中判断是否商品秒杀完
            boolean isOver =(boolean)redisUtil.get(Constant.REDIS_GOODS_MIAOSHA_OVER_PREFIX, miaoShaGoodsId + "");
            if(isOver){
                return "-1";
            }else{
                return "0";
            }
        }
    }
}
