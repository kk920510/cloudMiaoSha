package com.java1234.miaosha.controller;

import com.java1234.miaosha.entity.Order;
import com.java1234.miaosha.entity.R;
import com.java1234.miaosha.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 秒杀商品控制器
 * @author zk

 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 根据id查询秒杀商品详情
     * @param id
     * @return
     */
    @RequestMapping("/detail")
    public R detail(String id){
        Order order = orderService.findById(id);
        Map<String,Object> map=new HashMap<>();
        map.put("data",order);
        return R.ok(map);
    }

    /**
     * 查询秒杀结果
     * @param userId
     * @param miaoShaGoodsId
     * @return
     */
    @RequestMapping("/getMiaoShaResult")
    public String getMiaoShaResult(@RequestParam("userId") Integer userId, @RequestParam("miaoShaGoodsId") Integer miaoShaGoodsId){
        System.out.println("秒杀远程调用");
        return orderService.getMiaoShaResult(userId, miaoShaGoodsId);
    }
}
