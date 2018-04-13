package com.imooc.service.impl;

import com.imooc.exception.SellException;
import com.imooc.service.RedisLock;
import com.imooc.service.SecKillService;
import com.imooc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SecKillServiceImpl implements SecKillService {

    @Autowired
    private RedisLock redisLock;

    private final long TIMEOUT = 10 * 1000;

    static Map<String,Integer> products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;
    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456",10000);
        stock.put("123456",10000);
    }

    private String queryMap(String productId){
        return "国庆活动，皮蛋瘦肉粥特价，限量份"+products.get(productId)
                +",还剩:"+stock.get(productId)
                +";该商品成功下单的用户数量："+orders.size()+"人";
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if(!redisLock.lock(productId,String.valueOf(time))){
            throw new SellException(101,"哎哟喂，人太多，换个姿势再试试");
        }

        //1、查询库存，为0则活动结束
        int stockNum = stock.get(productId);
        if(stockNum == 0){
            throw  new SellException(100,"活动结束");
        }else {
            //2、下单
            orders.put(KeyUtil.genUniqueKey(),productId);
            //3、减库存
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
        }

        //解锁
        redisLock.unlock(productId,String.valueOf(time));
    }
}