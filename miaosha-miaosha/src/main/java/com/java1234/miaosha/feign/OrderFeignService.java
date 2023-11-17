package com.java1234.miaosha.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("miaosha-order")
public interface OrderFeignService {
    @RequestMapping("/order/getMiaoShaResult")
    public String getMiaoShaResult(@RequestParam("userId") Integer userId, @RequestParam("miaoShaGoodsId") Integer miaoShaGoodsId);


}
