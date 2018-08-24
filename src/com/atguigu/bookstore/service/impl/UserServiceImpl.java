package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();

	@Override
	public User login(User user) {
		return userDao.getUserByUsernameAndPassword(user);
	}

	@Override
	public boolean register(User user) {
		int count = userDao.saveUser(user);
		return count > 0;
	}

	@Override
	public boolean checkUsername(String username) {
		User user = userDao.getUserByUsername(username);
		return user == null;
	}

}
