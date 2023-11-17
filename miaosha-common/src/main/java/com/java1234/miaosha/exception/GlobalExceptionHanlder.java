package com.java1234.miaosha.exception;

import com.java1234.miaosha.entity.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * @author zk
 * @company Java知识分享网
 * @create 2021-01-29 15:16
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHanlder {


    @ExceptionHandler(value = Exception.class)
    public R exceptionHandler(HttpServletRequest request, Exception e){
        System.out.println("全局异常捕获");
        return R.error("服务端异常，请联系管理员"+"<br/>"+e.getMessage()+"<br/>"+e.getStackTrace());
    }
}
