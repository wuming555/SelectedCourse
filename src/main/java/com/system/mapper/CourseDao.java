package com.system.mapper;

import com.system.po.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao {
    List<Course> showCourse();

    List<Course> findCourse(String CourseName);
}
