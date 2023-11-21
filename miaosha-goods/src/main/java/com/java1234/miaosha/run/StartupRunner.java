package com.java1234.miaosha.run;

import com.java1234.miaosha.constant.Constant;
import com.java1234.miaosha.service.IMiaoShaGoodsService;
import com.java1234.miaosha.util.RedisUtil;
import com.java1234.miaosha.vo.MiaoShaGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 启动的时候加载秒杀商品库存信息和是否秒杀完标识
 * @author zk
 * @create 2023-03-11 9:39
 */
@Component("startupRunner")
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private IMiaoShaGoodsService miaoShaGoodsService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run(String... args) throws Exception {
        List<MiaoShaGoodsVo> miaoShaGoodsList = miaoShaGoodsService.listAll();
        //System.out.println("启动加载秒杀库存信息");
        for(MiaoShaGoodsVo miaoShaGoodsVo:miaoShaGoodsList){
            System.out.println(miaoShaGoodsVo.getId()+":"+miaoShaGoodsVo.getStock());
            redisUtil.set2(Constant.REDIS_STOCK_PREFIX,miaoShaGoodsVo.getId()+"",miaoShaGoodsVo.getStock()+"");
            redisUtil.set(Constant.REDIS_GOODS_MIAOSHA_OVER_PREFIX,miaoShaGoodsVo.getId()+"",false);
        }
    }
}
