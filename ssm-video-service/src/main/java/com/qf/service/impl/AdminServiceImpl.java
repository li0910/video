package com.qf.service.impl;

import com.qf.dao.AdminMapper;
import com.qf.pojo.Admin;
import com.qf.pojo.AdminExample;
import com.qf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Admin findAdmin(Admin admin1) {
        return adminMapper.findAdmin(admin1);
    }

    @Override
    public void updateAdmin(Admin admin) {
        AdminExample example = new AdminExample();

        AdminExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(admin.getId());

        adminMapper.updateByExample(admin,example);
    }
}
