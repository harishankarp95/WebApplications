package com.hari.app.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hari.app.config.SpringRootConfig;
import com.hari.app.dao.AppUserDAO;
import com.hari.app.domain.AppUser;

public class TestAppUserDAODelete {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		AppUserDAO userDao = ctx.getBean(AppUserDAO.class);
		
		userDao.delete(4);
		
		System.out.println("----------------Data Deleted Successfully-----------------");
	}
}
