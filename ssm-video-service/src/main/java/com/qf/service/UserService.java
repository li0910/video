package com.qf.service;

import com.qf.pojo.User;

public interface UserService {

    User showMyProfile(Integer id);

    User findByEmail(User user);

    void addUser(User user);

    User checkEmail(String email);

    void update(User user);

    void updateById(Integer id);

    User findAll(Integer id);


}
