package com.qf.service;

import com.qf.pojo.User;

public interface UserService {

	User selectByPrimaryKey(int id);

	User findByEmail(String email);

	void updateUser(User user);
}
