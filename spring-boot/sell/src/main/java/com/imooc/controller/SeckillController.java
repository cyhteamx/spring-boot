package com.imooc.controller;

import com.imooc.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
@Slf4j
public class SeckillController {

    @Autowired
    private SecKillService secKillService;

    /**
     * 查询秒杀的结果
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) throws Exception{
        return secKillService.querySecKillProductInfo(productId);
    }

    /**
     * 秒杀
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) throws Exception{
        secKillService.orderProductMockDiffUser(productId);
        return secKillService.querySecKillProductInfo(productId);
    }
}