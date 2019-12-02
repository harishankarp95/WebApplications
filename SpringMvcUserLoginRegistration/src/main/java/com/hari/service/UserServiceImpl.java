package com.hari.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hari.dao.UserDao;
import com.hari.model.Login;
import com.hari.model.User;

public class UserServiceImpl implements UserService{

	@Autowired
	public UserDao userDao;
	
	public void register(User user)
	{
		userDao.register(user);
	}
	
	public User validateUser(Login login)
	{
		return userDao.validateUser(login);
	}
}
