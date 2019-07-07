package com.system.mapper;

import com.system.po.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao {
    //查询学生信息，和其选课信息
    Student findStudentAndSelectCourseListById(Integer id) throws Exception;

    Student showStudentInfo(Integer id);

    int updateStudentInfo(Student student);
    void upload(Student student);
}
