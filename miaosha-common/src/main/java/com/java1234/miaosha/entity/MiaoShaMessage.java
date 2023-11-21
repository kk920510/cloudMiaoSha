package com.java1234.miaosha.entity;

/**
 * @author zk
 * @create 2023-11-02 19:57
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
