package com.igibgo.igibgo.controller;

import com.alibaba.fastjson.JSONObject;
import com.igibgo.igibgo.resp.BackstageUserResponse;
import com.igibgo.igibgo.service.BackstageLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class BackstageLoginController {

    @Resource
    private BackstageLoginService loginService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    private static final Logger LOG= LoggerFactory.getLogger(BackstageLoginController.class);

    @PostMapping("/backstage/login")
    public BackstageUserResponse loginByEmail(String email, String password) {
        BackstageUserResponse resp = loginService.loginByEmail(email, password);
        String newToken = UUID.randomUUID().toString();
        resp.setToken(newToken);
        redisTemplate.opsForValue().set(newToken, JSONObject.toJSONString(resp), 60, TimeUnit.MINUTES);
        LOG.info("Added new token: {}",newToken);
        return resp;
    }

    @GetMapping("/backstage/logout")
    public void logout(String token){
        redisTemplate.delete(token);
        LOG.info("Deleted token from redis: {}",token);
    }
}
