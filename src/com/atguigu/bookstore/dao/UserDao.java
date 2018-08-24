package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.User;

public interface UserDao {
	User getUserByUsernameAndPassword(User user);

	int saveUser(User user);

	User getUserByUsername(String username);
}
