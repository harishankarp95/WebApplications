package com.hari.app.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hari.app.config.SpringRootConfig;
import com.hari.app.dao.AppUserDAO;
import com.hari.app.domain.AppUser;

public class TestAppUserDAOSave {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		AppUserDAO userDao = ctx.getBean(AppUserDAO.class);
		//TODO: The User details will be taken from User- Reg-From 
		AppUser u = new AppUser();
		u.setName("Harishankar");
		u.setPhone("9274792020");
		u.setEmail("hari@itcs.com");
		u.setAddress("Bangalore");
		u.setLoginName("hari123");
		u.setPassword("hari143");
		u.setRole(1); // Admin Role
		u.setLoginStatus(1); // Active state
		try {
			userDao.save(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("----------------Data saved-----------------");
	}
}
