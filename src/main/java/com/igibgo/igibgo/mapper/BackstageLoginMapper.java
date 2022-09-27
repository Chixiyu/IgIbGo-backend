package com.igibgo.igibgo.mapper;

import com.igibgo.igibgo.entity.BackstageUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BackstageLoginMapper {
    //    note that here the user's password isn't checked
    @Select("select * from backend_users where email=#{email}")
    public BackstageUser loginByUsername(String email, String password);
}
