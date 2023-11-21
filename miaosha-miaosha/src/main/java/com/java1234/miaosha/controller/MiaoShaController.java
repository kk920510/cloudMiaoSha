package com.java1234.miaosha.controller;

import com.java1234.miaosha.constant.Constant;
import com.java1234.miaosha.entity.MiaoShaMessage;
import com.java1234.miaosha.entity.R;
import com.java1234.miaosha.entity.User;
import com.java1234.miaosha.feign.OrderFeignService;
import com.java1234.miaosha.rabbitmq.MQSender;
import com.java1234.miaosha.service.IMiaoShaGoodsService;
import com.java1234.miaosha.service.IMiaoShaService;
import com.java1234.miaosha.service.IOrderService;
import com.java1234.miaosha.util.RedisUtil;
import com.java1234.miaosha.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 秒杀控制器
 * @author zk
 * @create 2023-11-08 13:42
 */
@RestController
@RequestMapping("/miaoSha")
public class MiaoShaController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IMiaoShaGoodsService miaoShaGoodsService;

    @Autowired
    private IMiaoShaService miaoShaService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private MQSender mqSender;

    @Autowired
    private OrderFeignService orderFeignService;

    /**
     * 执行秒杀
     * @param request
     * @param miaoShaGoodsId
     * @return
     */
    @RequestMapping("/exec")
    public R exec(HttpServletRequest request, Integer miaoShaGoodsId,String verifyCode){
        if(StringUtil.isEmpty(verifyCode)){
            return R.error("验证码不能为空！");
        }
        // 第一步：根据token得到用户user对象
        String token = request.getHeader("token");
        System.out.println("token:"+token);
        User user=(User)redisUtil.get(Constant.REDIS_TOKEN_PREFIX,token);
        System.out.println(user);

        Object rnd=redisUtil.get(Constant.REDIS_VERIFYCODE_PREFIX,user.getId()+","+miaoShaGoodsId);
        if(rnd==null){
            return R.error("验证码过期，请重新点击刷新验证码！");
        }
        if(!verifyCode.equals(String.valueOf(rnd))){
            return R.error("验证码结果错误！");
        }

        boolean isOver =(boolean)redisUtil.get(Constant.REDIS_GOODS_MIAOSHA_OVER_PREFIX, miaoShaGoodsId + "");
        if(isOver){
            return R.ok("秒杀结束");
        }
        long stock=redisUtil.decr(Constant.REDIS_STOCK_PREFIX,miaoShaGoodsId + "",1);
        if(stock<0){
            return R.error("秒杀失败，商品已经秒光，欢迎参与！");
        }

        MiaoShaMessage mm=new MiaoShaMessage();
        mm.setUser(user);
        mm.setMiaoShaGoodsId(miaoShaGoodsId);
        mqSender.sendMiaoShaMessage(mm);

        return R.ok("排队中");
    }

    /**
     * 秒杀结果查询
     * @param request
     * @param miaoShaGoodsId
     * @return  >0 返回orderId 订单ID  -1 秒杀失败  0 排队中
     */
    @RequestMapping("/result")
    public R result(HttpServletRequest request,Integer miaoShaGoodsId){
        String token = request.getHeader("token");
        System.out.println("token:"+token);
        User user=(User)redisUtil.get(Constant.REDIS_TOKEN_PREFIX,token);
        System.out.println(user);
        String result=orderFeignService.getMiaoShaResult(user.getId(),miaoShaGoodsId);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("result",result);
        return R.ok(resultMap);
    }
}
