package com.java1234.miaosha.interceptor;

import com.java1234.miaosha.constant.Constant;
import com.java1234.miaosha.util.RedisUtil;
import com.java1234.miaosha.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权拦截器
 * @author zk
 * @company Java知识分享网
 * @create 2021-01-29 14:11
 */
public class SysInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path=request.getRequestURI();
        System.out.println(path);
        if(handler instanceof HandlerMethod){
            String token = request.getHeader("token");
            System.out.println("token:"+token);
            if(StringUtil.isEmpty(token)){
                System.out.println("token为空！");
                throw new RuntimeException("签名验证不存在");
            }else{
                Object o=redisUtil.get(Constant.REDIS_TOKEN_PREFIX,token);
                if(o!=null){
                    System.out.println("验证成功");
                    return true;
                }else{
                    System.out.println("验证失败");
                    throw new RuntimeException("签名失败");
                }
            }
        }else{
            return true;
        }
    }


}
