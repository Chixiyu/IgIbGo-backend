package com.igibgo.igibgo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DapeshSubjectsMapper {
    @Select("select * from subjects")
    public List<String> getAllSubjects();
}
