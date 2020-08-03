package com.hari.app.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hari.app.config.SpringRootConfig;
import com.hari.app.dao.AppUserDAO;
import com.hari.app.domain.AppUser;

public class TestAppUserDAOFindSingleRecord {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		AppUserDAO userDao = ctx.getBean(AppUserDAO.class);
		
		AppUser u = userDao.findbyId(2);
		System.out.println("-----------User Details-----------------");
		System.out.println(u.getUserId());
		System.out.println(u.getName());
		System.out.println(u.getEmail());
		System.out.println(u.getPhone());
		System.out.println(u.getAddress());
		System.out.println(u.getLoginName());
		System.out.println(u.getLoginStatus());
		
	}
}
