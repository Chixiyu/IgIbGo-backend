package com.igibgo.igibgo.controller;

import com.igibgo.igibgo.entity.Note;
import com.igibgo.igibgo.service.DapeshNotesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DapeshNotesController {

    @Resource
    private DapeshNotesService notesService;

    @GetMapping("/dapesh/notes/noteList")
    public List<Note> selectNotesBySubject(String subject){
        return notesService.selectNotesBySubject(subject);
    }

    @GetMapping("/dapesh/notes/getNote")
    public Note selectNoteById(Long id){
        System.out.println("noteId: "+id);
        return notesService.selectNoteById(id);
    }
}
