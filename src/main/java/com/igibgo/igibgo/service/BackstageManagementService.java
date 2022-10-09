package com.igibgo.igibgo.service;

import com.igibgo.igibgo.entity.Events;
import com.igibgo.igibgo.entity.Student;
import com.igibgo.igibgo.mapper.BackstageManagementMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BackstageManagementService {
    @Resource
    BackstageManagementMapper managementMapper;

    public List<Events> getAllEvents() {
        return managementMapper.getAllEvents();
    }

    public List<String> getAllSubjects(){
        return managementMapper.getAllSubjects();
    }

    public List<String> getStudentClasses(String subject){
        return managementMapper.getStudentClasses(subject);
    }

    public List<Student> getStudents(String subject, String studentClass){
        return managementMapper.getStudents(subject,studentClass);
    }
}
