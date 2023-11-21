package com.java1234.miaosha.controller;

import com.java1234.miaosha.constant.Constant;
import com.java1234.miaosha.entity.R;
import com.java1234.miaosha.entity.User;
import com.java1234.miaosha.service.IUserService;
import com.java1234.miaosha.util.Md5Util;
import com.java1234.miaosha.util.RedisUtil;
import com.java1234.miaosha.util.StringUtil;
import com.java1234.miaosha.util.UUIDUtil;
import com.java1234.miaosha.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * 用户登录控制器
 * @author zk
 * @create 2023-11-23 21:41
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 用户登录
     * @return
     */
    @RequestMapping("/login")
    public R login(@RequestBody  UserVo userVo){
        if(userVo==null){
            return R.error();
        }
        if(StringUtil.isEmpty(userVo.getUsername())){
            return R.error("用户名不能为空！");
        }
        if(StringUtil.isEmpty(userVo.getPassword())){
            return R.error("密码不能为空！");
        }
        User resultUser = userService.findByUserName(userVo.getUsername());
        if(resultUser==null){
            return R.error("用户名不存在！");
        }
        if(!resultUser.getPassword().trim().equals(Md5Util.backMd5(userVo.getPassword().trim()))){
            return R.error("用户名或者密码错误！");
        }
        String token=UUIDUtil.genUuid(); // 生成token
        redisUtil.set(Constant.REDIS_TOKEN_PREFIX,token,resultUser,Constant.REDIS_TOKEN_EXPIRE);
        return R.ok(token);
    }


    /**
     * 注册2千用户
     * @return
     */
    @RequestMapping("/register")
    public R register()throws Exception{
        for(int i=0;i<2000;i++){
            User user=new User();
            user.setUsername("user"+i);
            user.setPassword("37cbc2f0be822f5ab96485ac11f3dc98");
            // 生成token
            String token=UUIDUtil.genUuid();

            // 插入数据库
            //userService.save(user);

            redisUtil.set(Constant.REDIS_TOKEN_PREFIX,token,user,Constant.REDIS_TOKEN_EXPIRE);
            System.out.println(token);

            // 把用户名和token写入txt文件
            addUserToTxt(user.getUsername(),token);


        }
        return R.ok();
    }



    /**
     * 把用户名和token写入txt文件
     * @param username
     * @param token
     */
    private void addUserToTxt(String username, String token) throws Exception{
        String arr=username+","+token;
        FileWriter fw=new FileWriter(new File("E:/0soft/jmeter/user.txt"),true);
        BufferedWriter bw=new BufferedWriter(fw);
        bw.write(arr+"\r\n");
        bw.flush();
        bw.close();
        fw.close();
    }


}
