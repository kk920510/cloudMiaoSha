package com.java1234.miaosha.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户vo
 * @author zk
 * @create 2023-12-17 19:26
 */
@Data
public class UserVo implements Serializable {

    private String username; // 用户名

    private String password; // 密码

}
