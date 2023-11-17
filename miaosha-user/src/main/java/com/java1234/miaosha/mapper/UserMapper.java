package com.java1234.miaosha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java1234.miaosha.entity.User;

/**
 * 用户Mapper接口
 * @author zk
 * @company Java知识分享网
 * @create 2021-01-23 21:34
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public User findById(Integer id);
}
