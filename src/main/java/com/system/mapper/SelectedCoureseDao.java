package com.system.mapper;

import com.system.po.SelectedCourse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectedCoureseDao {
    int countByExample(SelectedCourse course);

    int delete(Integer cid);

    int insert(SelectedCourse record);

    int insertSelective(SelectedCourse record);

    List<SelectedCourse> selectAll(SelectedCourse course);

    SelectedCourse findRecord(SelectedCourse selectedCourse);


}
