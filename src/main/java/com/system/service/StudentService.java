package com.system.service;

import com.system.po.Student;

public interface StudentService {
    Student findStudentAndSelectCourseListById(Integer id) throws Exception;

    int updateStudentInfo(Student student);

    Student showStudentINfo(Integer id);
    void upload(Student student);
}
