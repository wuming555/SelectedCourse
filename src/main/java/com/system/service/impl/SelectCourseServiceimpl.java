package com.system.service.impl;

import com.system.mapper.SelectedCoureseDao;
import com.system.po.SelectedCourse;
import com.system.service.SelectCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SelectCourseServiceimpl implements SelectCourseService {
    @Resource
    private SelectedCoureseDao selectedCoureseDao;

    @Override
    public void save(SelectedCourse selectedCourse) throws Exception {
        selectedCoureseDao.insert(selectedCourse);
    }

    @Override
    public void remove(Integer cid) throws Exception {
        selectedCoureseDao.delete(cid);
    }

    @Override
    public SelectedCourse findone(SelectedCourse selectedCourse) {
        return selectedCoureseDao.findRecord(selectedCourse);
    }
}
