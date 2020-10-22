package com.qf.service.impl;

import com.qf.dao.SubjectMapper;
import com.qf.pojo.Subject;
import com.qf.pojo.SubjectExample;
import com.qf.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public List<Subject> selectByExample(SubjectExample example) {
        return subjectMapper.selectByExample(example);
    }

    @Override
    public Subject findById(int id) {
        return subjectMapper.findById(id);
    }

    @Override
    public Subject findByName(String subjectName) {
        return subjectMapper.findByName(subjectName);
    }
}
