package com.qf.service.impl;

import com.qf.dao.CourseMapper;
import com.qf.pojo.Course;
import com.qf.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course findOneCourse(int id) {
//        System.out.println("z" + courseMapper.findOneCourse(id));
        return courseMapper.findOneCourse(id);

    }

    @Override
    public List<Course> findAllCourse(int id) {
        return courseMapper.findAllCourse(id);
    }


}
