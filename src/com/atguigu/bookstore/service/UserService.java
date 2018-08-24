package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.User;

public interface UserService {
	User login(User user);

	boolean register(User user);

	/**
	 * 检测用户名是否已存在
	 * 
	 * @param username
	 * @return true 为用户名可用
	 */
	boolean checkUsername(String username);
}
