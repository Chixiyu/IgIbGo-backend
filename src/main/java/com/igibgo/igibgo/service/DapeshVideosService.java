package com.igibgo.igibgo.service;

import com.igibgo.igibgo.entity.Video;
import com.igibgo.igibgo.mapper.DapeshVideosMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DapeshVideosService {

    @Resource
    private DapeshVideosMapper videosMapper;

    public List<Video> selectAllDapeshVideos(){
        return videosMapper.selectAllDapeshVideos();
    }

    public Video selectVideoById(Long id){
        return videosMapper.selectVideoById(id);
    }
}
