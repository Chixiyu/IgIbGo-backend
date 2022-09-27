package com.igibgo.igibgo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Repository
public interface BackstageUploadMapper {

    @Transactional
    @Insert("insert into dapesh_videos (name, position, cover, subtitle, description) values (#{name},#{position},#{cover},#{subtitle},#{description})")
    void addDapeshVideos(String name, String position, String cover, String subtitle, String description);


    @Transactional
    @Insert("insert into dapesh_notes (name, position, author,subject) values(#{name},#{position},#{author},#{subject})")
    void addDapeshNotes(String name,String position,String author,String subject);
}
