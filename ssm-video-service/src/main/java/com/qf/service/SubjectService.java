package com.qf.service;

import com.qf.pojo.Subject;

import java.util.List;

import com.qf.pojo.SubjectExample;

public interface SubjectService {
    List<Subject> findAll(Object o);

	List<Subject> selectByExample(SubjectExample example);

	Subject findById(int id);

	Subject findByName(String subjectName);
}
