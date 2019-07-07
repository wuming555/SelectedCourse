package com.system.service.impl;

import com.system.mapper.CourseDao;
import com.system.po.Course;
import com.system.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;

    public List<Course> AllCourse() {
        return courseDao.showCourse();
    }

    @Override
    public List<Course> findCourse(String courseName) {
        return courseDao.findCourse(courseName);
    }
}
