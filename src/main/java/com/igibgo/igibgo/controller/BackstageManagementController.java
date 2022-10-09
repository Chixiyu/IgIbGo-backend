package com.igibgo.igibgo.controller;

import com.igibgo.igibgo.entity.Events;
import com.igibgo.igibgo.entity.Student;
import com.igibgo.igibgo.service.BackstageManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BackstageManagementController {

    @Resource
    private BackstageManagementService managementService;

    @GetMapping("/backstage/management/overview")
    public List<Events> getAllEvents(){
        return managementService.getAllEvents();
    }

    @GetMapping("/backstage/management/subjects")
    public List<String> getAllSubjects(){
        return managementService.getAllSubjects();
    }

    @GetMapping("/backstage/management/studentClasses")
    public List<String> getStudentClasses(String subject){
        return managementService.getStudentClasses(subject);
    }

    @GetMapping("/backstage/management/students")
    public List<Student> getStudents(String subject, String studentClass){
        return managementService.getStudents(subject,studentClass);
    }
}
