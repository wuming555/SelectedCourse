package com.system.mapper;

import com.system.po.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao {
    void insertTeacher(Teacher t);
}
