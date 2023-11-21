package com.java1234.miaosha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java1234.miaosha.entity.User;

/**
 * 用户Service接口
 * @author zk
 * @create 2023-11-23 21:36
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    public User findByUserName(String userName);
}
