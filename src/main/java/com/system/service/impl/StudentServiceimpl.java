package com.system.service.impl;

import com.system.mapper.StudentDao;
import com.system.po.Student;
import com.system.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceimpl implements StudentService {
    @Resource

    private StudentDao studentDao;

    @Override
    public Student findStudentAndSelectCourseListById(Integer id) throws Exception {
        return studentDao.findStudentAndSelectCourseListById(id);
    }

    @Override
    public int updateStudentInfo(Student student) {
        return studentDao.updateStudentInfo(student);
    }

    @Override
    public Student showStudentINfo(Integer id) {
        return studentDao.showStudentInfo(id);
    }

    @Override
    public void upload(Student s) {

        studentDao.upload(s);
    }
}