package com.java1234.miaosha.service;


import com.java1234.miaosha.entity.User;
import com.java1234.miaosha.vo.MiaoShaGoodsVo;


/**
 * 秒杀service接口
 * @author zk
 * @company Java知识分享网
 * @create 2021-01-30 15:25
 */
public interface IMiaoShaService {

    /**
     * 执行秒杀
     * @param user
     * @param miaoShaGoodsVo
     * @return
     */
    public String miaoSha(User user, MiaoShaGoodsVo miaoShaGoodsVo);

}
