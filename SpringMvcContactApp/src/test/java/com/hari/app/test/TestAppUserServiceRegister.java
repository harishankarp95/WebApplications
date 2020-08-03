package com.hari.app.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hari.app.config.SpringRootConfig;
import com.hari.app.domain.AppUser;
import com.hari.app.service.AppUserService;

public class TestAppUserServiceRegister {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		AppUserService userService=ctx.getBean(AppUserService.class);
		//TODO: The User details will be taken from User- Reg-From 
		AppUser u = new AppUser();
		u.setName("Rakesh");
		u.setPhone("333333222");
		u.setEmail("Rakesh@itcs.com");
		u.setAddress("Andhra Pradesh");
		u.setLoginName("Rakesh123");
		u.setPassword("RRRRRR");
		u.setRole(AppUserService.ROLE_ADMIN); // Admin Role
		u.setLoginStatus(AppUserService.LOGIN_STATUS_ACTIVE); // Active state
		userService.register(u);
		
		System.out.println("----------------Data saved-----------------");
	}
}
