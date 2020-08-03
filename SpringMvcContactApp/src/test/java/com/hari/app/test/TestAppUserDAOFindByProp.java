package com.hari.app.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hari.app.config.SpringRootConfig;
import com.hari.app.dao.AppUserDAO;
import com.hari.app.domain.AppUser;

public class TestAppUserDAOFindByProp {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		AppUserDAO userDao = ctx.getBean(AppUserDAO.class);
		
		//Search from column wise - Find by property
		
		//List<AppUser> users=userDao.findByProperty("userId", 1);
		//List<AppUser> users=userDao.findByProperty("name", "Hari Pradhan");
		List<AppUser> users=userDao.findByProperty("role", 1);
		
		for (AppUser appUser : users) {
			System.out.println("-----------User Details-----------------");
			System.out.println(appUser.getUserId());
			System.out.println(appUser.getName());
			System.out.println(appUser.getEmail());
			System.out.println(appUser.getPhone());
			System.out.println(appUser.getAddress());
			System.out.println(appUser.getLoginName());
			System.out.println(appUser.getLoginStatus());
			System.out.println(appUser.getRole());
		}
		
	}
}
