package com.igibgo.igibgo.controller;

import com.igibgo.igibgo.service.DapeshSubjectsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DapeshSubjectListController {

    @Resource
    DapeshSubjectsService subjectsService;

    @GetMapping("/dapesh/subjects")
    public List<String> getAllSubjects(){
        return subjectsService.selectAllSubjects();
    }
}
