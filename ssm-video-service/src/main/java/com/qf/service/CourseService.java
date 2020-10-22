package com.qf.service;

import com.qf.pojo.Course;

import java.util.List;

public interface CourseService {
	Course findOneCourse(int id);

	List<Course> findAllCourse(int id);
    List<Course> findAll(Object o);

    void updateCourse(Course course);

    void insertCourse(Course course);

    Course findById(Integer id);
}
