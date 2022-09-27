package com.igibgo.igibgo.service;

import com.igibgo.igibgo.entity.BackstageUser;
import com.igibgo.igibgo.mapper.BackstageLoginMapper;
import com.igibgo.igibgo.resp.BackstageUserResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BackstageLoginService {
    @Resource
    private BackstageLoginMapper loginMapper;
    /* 3 login status:
    0. login failed
    1. login successful
    2. assigned password
     */
    public BackstageUserResponse loginByEmail(String email, String password){
        try{
            BackstageUser user=loginMapper.loginByUsername(email,password);
            if(user==null){
                BackstageUserResponse resp=new BackstageUserResponse();
                resp.setStatus(0);
                return resp;
            } else if (!password.equals(user.getPassword())) {
                BackstageUserResponse resp=new BackstageUserResponse();
                resp.setStatus(0);
                return resp;
            } else{
                return new BackstageUserResponse(1,
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getAdmin(),
                        user.getDapesh());
            }
        }
        catch (Exception e){
            e.printStackTrace();
            BackstageUserResponse resp=new BackstageUserResponse();
            resp.setStatus(0);
            return resp;
        }
    }
}
