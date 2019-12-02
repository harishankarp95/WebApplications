package com.hari.dao;

import com.hari.model.Login;
import com.hari.model.User;

public interface UserDao {
	
	void register(User user);
	User validateUser(Login login);
}
