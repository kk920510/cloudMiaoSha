package com.java1234.miaosha.controller;

import com.java1234.miaosha.constant.Constant;
import com.java1234.miaosha.entity.R;
import com.java1234.miaosha.service.IMiaoShaGoodsService;
import com.java1234.miaosha.util.RedisUtil;
import com.java1234.miaosha.vo.MiaoShaGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 秒杀商品控制器
 * @author zk
 */
@RestController
@RequestMapping("/miaoShaGoods")
public class MiaoShaGoodsController {

    @Autowired
    private IMiaoShaGoodsService miaoShaGoodsService;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 查询所有秒杀商品
     * @return
     */
    @RequestMapping("/findAll")
    public R findAll(){
        List<MiaoShaGoodsVo> miaoShaGoodsList=null;
        Object o=redisUtil.get(Constant.REDIS_MIAOSHA_GOODS);
        if(o==null){
            System.out.println("从数据库里面查询");
            miaoShaGoodsList = miaoShaGoodsService.listAll();
            redisUtil.set(Constant.REDIS_MIAOSHA_GOODS,miaoShaGoodsList, Constant.REDIS_MIAOSHA_GOODS_EXPIRE);
        }else{
            System.out.println("从redis中取值");
            miaoShaGoodsList= (List<MiaoShaGoodsVo>) o;
        }
        Map<String,Object> map=new HashMap<>();
        map.put("data",miaoShaGoodsList);
        return R.ok(map);
    }

    /**
     * 根据id查询秒杀商品详情
     * @param id
     * @return
     */
    @RequestMapping("/detail")
    public R detail(Integer id){
        MiaoShaGoodsVo miaoShaGoodsVo = miaoShaGoodsService.findById(id);
        Integer miaoShaStatus=0; // 秒杀状态
        Integer remainBeginSecond=0; // 剩余多少秒 秒杀开始
        Integer remainEndSecond=0; // 秒杀结束 剩余多少秒

        long startTime = miaoShaGoodsVo.getStartTime().getTime();
        long endTime = miaoShaGoodsVo.getEndTime().getTime();
        long currentTime=System.currentTimeMillis();
        System.out.println("startTime:"+startTime);
        System.out.println("endTime:"+endTime);
        System.out.println("currentTime:"+currentTime);

        if(currentTime<startTime){ // 秒杀还没开始 倒计时
            miaoShaStatus=0;
            remainBeginSecond=(int)(startTime-currentTime)/1000;
            remainEndSecond=(int)(endTime-currentTime)/1000;
        }else if(currentTime>endTime){ // 秒杀结束
            miaoShaStatus=2;
            remainBeginSecond=-1;
            remainEndSecond=-1;
        }else{ // 秒杀进行中
            miaoShaStatus=1;
            remainBeginSecond=0;
            remainEndSecond=(int)(endTime-currentTime)/1000;
        }

        miaoShaGoodsVo.setMiaoShaStatus(miaoShaStatus);
        miaoShaGoodsVo.setRemainBeginSecond(remainBeginSecond);
        miaoShaGoodsVo.setRemainEndSecond(remainEndSecond);

        Map<String,Object> map=new HashMap<>();
        map.put("data",miaoShaGoodsVo);
        return R.ok(map);
    }
}
