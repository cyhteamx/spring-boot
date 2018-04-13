package com.imooc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @RequestMapping(value = "/wx")
    public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.error("WechatController   ----   WechatController");

        System.out.println("========WechatController========= ");
        log.info("请求进来了...");

        Enumeration pNames = request.getParameterNames();
        while (pNames.hasMoreElements()) {
            String name = (String) pNames.nextElement();
            String value = request.getParameter(name);
            // out.print(name + "=" + value);

            String log1 = "name =" + name + "     value =" + value;
            log.error(log1);
        }

        PrintWriter out = response.getWriter();
        String signature = request.getParameter("signature");/// 微信加密签名
        String timestamp = request.getParameter("timestamp");/// 时间戳
        String nonce = request.getParameter("nonce"); /// 随机数
        String echostr = request.getParameter("echostr"); // 随机字符串

        out.print(echostr);

        out.close();
        out = null;

    }

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法。。。");
        log.info("code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1d8cb1c91eacd4d1&secret=54ed4a0847638f6730d7c4621166edc7&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }
}
