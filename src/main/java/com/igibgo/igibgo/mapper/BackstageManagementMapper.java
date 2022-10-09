package com.igibgo.igibgo.mapper;

import com.igibgo.igibgo.entity.Events;
import com.igibgo.igibgo.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BackstageManagementMapper {
    @Select("select * from school_events")
    public List<Events> getAllEvents();

    @Select("select * from subjects")
    public List<String> getAllSubjects();

    @Select("select distinct student_class from students where subject=#{subject}")
    public List<String> getStudentClasses(String subject);

    @Select("select * from students where subject=#{subject} and student_class=#{studentClass}")
    public List<Student> getStudents(String subject, String studentClass);
}
