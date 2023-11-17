package com.java1234.miaosha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java1234.miaosha.entity.MiaoShaGoods;
import com.java1234.miaosha.vo.MiaoShaGoodsVo;

import java.util.List;

/**
 * 秒杀商品Mapper接口
 * @author zk
 * @company Java知识分享网
 * @create 2021-01-30 15:20
 */
public interface MiaoShaGoodsMapper extends BaseMapper<MiaoShaGoods> {

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

    /**
     * 商品库存减1操作
     * @param id
     * @return
     */
    public Integer reduceStock(Integer id);
}
