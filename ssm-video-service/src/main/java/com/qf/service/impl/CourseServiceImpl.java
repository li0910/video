package com.qf.service.impl;

import com.qf.dao.CourseMapper;
import com.qf.pojo.Course;
import com.qf.pojo.CourseExample;
import com.qf.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findAll(Object o) {
        return courseMapper.selectByExample(null);
    }

    @Override
    public void updateCourse(Course course) {
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(course.getId());
        criteria.andCourseTitleLike(course.getCourseTitle());

        courseMapper.updateByExample(course,example);

    }

    @Override
    public void insertCourse(Course course) {
        courseMapper.insert(course);
    }

    @Override
    public Course findById(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }
}
