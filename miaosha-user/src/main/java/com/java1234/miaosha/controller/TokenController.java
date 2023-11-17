package com.java1234.miaosha.controller;

import com.java1234.miaosha.constant.Constant;
import com.java1234.miaosha.entity.R;
import com.java1234.miaosha.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * token操作控制器
 * @author zk
 * @company Java知识分享网
 * @create 2021-01-28 13:08
 */
@RestController
@RequestMapping("/")
public class TokenController {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * token续期
     * @param request
     * @return
     */
    @GetMapping(value = "/refreshToken")
    public R refreshToken(HttpServletRequest request){
        String token=request.getHeader("token");
        //System.out.println("a有效期："+redisUtil.getExpire(Constant.REDIS_TOKEN_PREFIX+token));
        redisUtil.expire(Constant.REDIS_TOKEN_PREFIX,token, Constant.REDIS_TOKEN_EXPIRE);
        //System.out.println("b有效期："+redisUtil.getExpire(Constant.REDIS_TOKEN_PREFIX+token));
        return R.ok();
    }
}
