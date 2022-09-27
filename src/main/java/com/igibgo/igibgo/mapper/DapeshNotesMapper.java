package com.igibgo.igibgo.mapper;

import com.igibgo.igibgo.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DapeshNotesMapper {
    @Select("select * from dapesh_notes where subject=#{subject}")
    public List<Note> selectNotesBySubject(String subject);

    @Select("select * from dapesh_notes where id=#{id}")
    public Note selectNoteById(Long id);
}
