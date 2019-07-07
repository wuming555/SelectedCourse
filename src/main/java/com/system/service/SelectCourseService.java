package com.system.service;

import com.system.po.SelectedCourse;

public interface SelectCourseService {
    //选课
    void save(SelectedCourse selectedCourse) throws Exception;

    //退课
    void remove(Integer cid) throws Exception;

    //查看
    SelectedCourse findone(SelectedCourse selectedCourse);
}
