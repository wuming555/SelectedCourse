package com.system.service.impl;

import com.system.mapper.TeacherDao;
import com.system.po.Teacher;
import com.system.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherDao teacherDao;

    @Override
    public void insert(Teacher t) {
        teacherDao.insertTeacher(t);
    }
}
