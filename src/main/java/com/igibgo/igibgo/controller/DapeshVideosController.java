package com.igibgo.igibgo.controller;

import com.igibgo.igibgo.entity.Video;
import com.igibgo.igibgo.service.DapeshVideosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DapeshVideosController {

    @Resource
    private DapeshVideosService videoService;

    @GetMapping("/dapesh/videos/videoList")
    public List<Video> selectAllDapeshVideos(){
        return videoService.selectAllDapeshVideos();
    }

    @GetMapping("/dapesh/videos/getVideo")
    public Video selectVideoById(Long id){
        return videoService.selectVideoById(id);
    }
}
