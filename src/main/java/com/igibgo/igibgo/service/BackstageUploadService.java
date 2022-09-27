package com.igibgo.igibgo.service;

import com.igibgo.igibgo.mapper.BackstageUploadMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BackstageUploadService {

    @Resource
    private BackstageUploadMapper uploadMapper;

    public void videoInfoForm(String name, String position, String cover, String subtitle, String description) {
        uploadMapper.addDapeshVideos(name, position, cover, subtitle, description);
    }

    public void noteInfoForm(String name,String position,String author,String subject){
        uploadMapper.addDapeshNotes(name,position,author,subject);
    }


}
