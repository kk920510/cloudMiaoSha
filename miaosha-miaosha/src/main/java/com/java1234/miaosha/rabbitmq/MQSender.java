package com.java1234.miaosha.rabbitmq;

import com.java1234.miaosha.config.MQConfig;
import com.java1234.miaosha.entity.MiaoShaMessage;
import com.java1234.miaosha.util.BeanUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zk
 * @company Java知识分享网
 * @create 2021-02-27 21:02
 */
@Service
public class MQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMiaoShaMessage(MiaoShaMessage miaoShaMessage){
        String msg = BeanUtil.beanToString(miaoShaMessage);
        System.out.println("send message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE,msg);
    }
}
