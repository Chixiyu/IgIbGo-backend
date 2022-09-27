package com.igibgo.igibgo.service;

import com.igibgo.igibgo.entity.Note;
import com.igibgo.igibgo.mapper.DapeshNotesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DapeshNotesService {
    @Resource
    DapeshNotesMapper notesMapper;

    public List<Note> selectNotesBySubject(String subject){
        List<Note> notes=notesMapper.selectNotesBySubject(subject);
        return notes;
    }

    public Note selectNoteById(Long id){
        Note note=notesMapper.selectNoteById(id);
        return note;
    }
}
