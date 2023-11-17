package com.java1234.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java1234.miaosha.entity.MiaoShaGoods;
import com.java1234.miaosha.vo.MiaoShaGoodsVo;

import java.util.List;

/**
 * 秒杀商品service接口
 * @author zk
 * @company Java知识分享网
 * @create 2021-01-30 15:25
 */
public interface IMiaoShaGoodsService extends IService<MiaoShaGoods> {

    /**
     * 查询所有秒杀商品
     * @return
     */
    public List<MiaoShaGoodsVo> listAll();

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    public MiaoShaGoodsVo findById(Integer id);
}
