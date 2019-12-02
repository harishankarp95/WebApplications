package com.hari.service;

import com.hari.model.Login;
import com.hari.model.User;

public interface UserService {

	void register(User user);
	
	User validateUser(Login login);
}
