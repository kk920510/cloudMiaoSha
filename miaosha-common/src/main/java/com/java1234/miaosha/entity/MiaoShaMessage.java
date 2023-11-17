package com.java1234.miaosha.entity;

/**
 * @author zk
 * @company Java知识分享网
 * @create 2021-01-02 19:57
 */
public class MiaoShaMessage {

    private User user;
    private Integer miaoShaGoodsId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getMiaoShaGoodsId() {
        return miaoShaGoodsId;
    }

    public void setMiaoShaGoodsId(Integer miaoShaGoodsId) {
        this.miaoShaGoodsId = miaoShaGoodsId;
    }
}
