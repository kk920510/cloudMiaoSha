package com.java1234.miaosha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java1234.miaosha.entity.Goods;

/**
 * 商品Mapper接口
 * @author zk
 * @create 2023-11-30 15:19
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    public Goods findById(Integer id);
}
