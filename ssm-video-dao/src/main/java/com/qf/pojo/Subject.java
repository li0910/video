package com.qf.pojo;

import lombok.Data;

@Data

public class Subject {
    private Integer id;

    private String subjectName;

    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

//    public Subject setCourse1(Course course) {
//        return Subject.this;
//    }
}