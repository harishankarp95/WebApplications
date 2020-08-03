package com.hari.app.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hari.app.config.SpringRootConfig;
import com.hari.app.dao.AppUserDAO;
import com.hari.app.domain.AppUser;

public class TestAppUserDAOUpdate {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		AppUserDAO userDao = ctx.getBean(AppUserDAO.class);
		//TODO: The User details will be taken from update profile page  
		AppUser u = new AppUser();
		u.setUserId(2);
		u.setName("Hari Pradhan");
		u.setPhone("0745783626");
		u.setEmail("hari@egmail.com");
		u.setAddress("Bangalore, BEL Road");
		u.setRole(1); // Admin Role
		u.setLoginStatus(1); // Active state
		
		userDao.update(u);
		
		System.out.println("----------------Data Updated Successfully-----------------");
	}
}
