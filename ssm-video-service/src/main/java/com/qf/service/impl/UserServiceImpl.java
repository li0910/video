package com.qf.service.impl;

import com.qf.dao.UserMapper;
import com.qf.pojo.User;
import com.qf.pojo.UserExample;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User showMyProfile(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findByEmail(User user) {
        return userMapper.findByEmail(user);
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User checkEmail(String email) {
        return userMapper.checkEmail(email);
    }

    @Override
    public void update(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(user.getId());

       userMapper.updateByExample(user, example);
    }

    @Override
    public void updateById(Integer id) {
        userMapper.updateImg(id);
    }

    @Override
    public User findAll(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
