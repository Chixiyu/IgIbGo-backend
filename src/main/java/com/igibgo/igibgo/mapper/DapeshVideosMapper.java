package com.igibgo.igibgo.mapper;

import com.igibgo.igibgo.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DapeshVideosMapper {
    @Select("select * from dapesh_videos")
    public List<Video> selectAllDapeshVideos();

    @Select("select * from dapesh_videos where id=#{id}")
    public Video selectVideoById(Long id);
}
