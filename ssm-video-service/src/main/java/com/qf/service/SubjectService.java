package com.qf.service;

import com.qf.pojo.Subject;
import com.qf.pojo.SubjectExample;

import java.util.List;

public interface SubjectService {

	List<Subject> selectByExample(SubjectExample example);

	Subject findById(int id);

	Subject findByName(String subjectName);
}
