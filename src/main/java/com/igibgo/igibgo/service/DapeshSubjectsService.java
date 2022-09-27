package com.igibgo.igibgo.service;

import com.igibgo.igibgo.mapper.DapeshSubjectsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DapeshSubjectsService {

    @Resource
    private DapeshSubjectsMapper subjectsMapper;

    public List<String> selectAllSubjects(){
        return subjectsMapper.getAllSubjects();
    }
}
