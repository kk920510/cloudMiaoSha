package com.java1234.miaosha.service.impl;

import com.java1234.miaosha.entity.Order;
import com.java1234.miaosha.entity.User;
import com.java1234.miaosha.mapper.MiaoShaGoodsMapper;
import com.java1234.miaosha.mapper.OrderMapper;
import com.java1234.miaosha.service.IMiaoShaService;
import com.java1234.miaosha.util.DateUtil;
import com.java1234.miaosha.util.RedisUtil;
import com.java1234.miaosha.vo.MiaoShaGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 秒杀Service实现类
 *
 * @author zk
 * @create 2023-11-10 13:30
 */
@Service("miaoShaService")
public class MiaoShaServiceImpl implements IMiaoShaService {

    @Autowired
    private MiaoShaGoodsMapper miaoShaGoodsMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 如果执行成功，返回订单ID，否则返回Null
     *
     * @param user
     * @param miaoShaGoodsVo
     * @return
     */
    @Override
    @Transactional
    public String miaoSha(User user, MiaoShaGoodsVo miaoShaGoodsVo) {
        // 库存减1操作
        Integer affectedRows = miaoShaGoodsMapper.reduceStock(miaoShaGoodsVo.getId());
        if (affectedRows == 0) {
            return null;
        }

        // 生成订单
        Order order = new Order();
        order.setId(DateUtil.getCurrentDateStr()); // 生成订单号
        order.setUser(user);
        order.setMiaoShaGoods(miaoShaGoodsVo);
        order.setCount(1);
        order.setTotalPrice(miaoShaGoodsVo.getPrice());
        order.setPayMethod(null);
        order.setStatus(0);
        Integer affectedRows2 = orderMapper.add(order);
        if (affectedRows2 == 0) {
            return null;
        }
        return order.getId();
    }


}
