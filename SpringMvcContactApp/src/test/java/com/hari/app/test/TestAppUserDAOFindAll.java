package com.hari.app.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hari.app.config.SpringRootConfig;
import com.hari.app.dao.AppUserDAO;
import com.hari.app.domain.AppUser;

public class TestAppUserDAOFindAll {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		AppUserDAO userDao = ctx.getBean(AppUserDAO.class);
		
		List<AppUser> users=userDao.findAll();
		for (AppUser appUser : users) {
			System.out.println("-----------User Details-----------------");
			System.out.println(appUser.getUserId());
			System.out.println(appUser.getName());
			System.out.println(appUser.getEmail());
			System.out.println(appUser.getPhone());
			System.out.println(appUser.getAddress());
			System.out.println(appUser.getLoginName());
			System.out.println(appUser.getLoginStatus());
		}
		
	}
}
