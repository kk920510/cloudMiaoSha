package com.java1234.miaosha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java1234.miaosha.entity.MiaoShaGoods;
import com.java1234.miaosha.mapper.MiaoShaGoodsMapper;
import com.java1234.miaosha.service.IMiaoShaGoodsService;
import com.java1234.miaosha.vo.MiaoShaGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 秒杀商品service实现类
 * @author zk
 * @create 2023-11-30 15:26
 */
@Service("miaoshaGoodsService")
public class MiaoShaGoodsServiceImpl extends ServiceImpl<MiaoShaGoodsMapper,MiaoShaGoods> implements IMiaoShaGoodsService {

    @Autowired
    private MiaoShaGoodsMapper miaoShaGoodsMapper;

    @Override
    public List<MiaoShaGoodsVo> listAll() {
        return miaoShaGoodsMapper.listAll();
    }

    @Override
    public MiaoShaGoodsVo findById(Integer id) {
        return miaoShaGoodsMapper.findById(id);
    }
}
