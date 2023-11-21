package com.java1234.miaosha.rabbitmq;

import com.java1234.miaosha.config.MQConfig;
import com.java1234.miaosha.constant.Constant;
import com.java1234.miaosha.entity.MiaoShaMessage;
import com.java1234.miaosha.entity.Order;
import com.java1234.miaosha.entity.User;
import com.java1234.miaosha.service.IMiaoShaGoodsService;
import com.java1234.miaosha.service.IMiaoShaService;
import com.java1234.miaosha.service.IOrderService;
import com.java1234.miaosha.util.BeanUtil;
import com.java1234.miaosha.util.RedisUtil;
import com.java1234.miaosha.vo.MiaoShaGoodsVo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author zk
 * @create 2023-11-17 21:05
 */
@Service
public class MQReceiver {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IMiaoShaGoodsService miaoShaGoodsService;

    @Autowired
    private IMiaoShaService miaoShaService;

    @Autowired
    private IOrderService orderService;

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receiveMiaoShaMessage(String message){
        MiaoShaMessage miaoShaMessage = BeanUtil.stringToBean(message, MiaoShaMessage.class);
        System.out.println("receive message:"+miaoShaMessage);
        User user = miaoShaMessage.getUser();

        Integer miaoShaGoodsId=miaoShaMessage.getMiaoShaGoodsId();

        // 第二步：判断库存是否足够
        MiaoShaGoodsVo miaoShaGoods = miaoShaGoodsService.findById(miaoShaGoodsId);
        Integer stock = miaoShaGoods.getStock();
        if(stock<=0){
            redisUtil.set(Constant.REDIS_GOODS_MIAOSHA_OVER_PREFIX,miaoShaGoodsId+"",true);
            System.out.println("库存不足！");
            return;
        }

        // 第三步：判断用户是否重复秒杀
        HashMap map=new HashMap();
        map.put("user_id",user.getId());
        map.put("miaosha_goods_id",miaoShaGoodsId);
        Order orderE =orderService.findByUserIdAndMiaoShaGoodsId(map);
        if(orderE!=null){
            System.out.println("您已经秒杀过此商品，不能重复秒杀");
            return;
        }

        // 第四步：减库存，下订单，必须同一个事务
        miaoShaService.miaoSha(user,miaoShaGoods);

    }
}
