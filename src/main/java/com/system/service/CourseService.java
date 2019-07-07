package com.system.service;

import com.system.po.Course;

import java.util.List;

public interface CourseService {
    List<Course> AllCourse();

    List<Course> findCourse(String courseName);
}
