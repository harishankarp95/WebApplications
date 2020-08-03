package com.hari.app.service;

import java.util.List;

import com.hari.app.domain.AppUser;
import com.hari.app.exception.AppUserBlockedException;

public interface AppUserService {
	
	public static final Integer LOGIN_STATUS_ACTIVE = 1;
    public static final Integer LOGIN_STATUS_BLOCKED = 2;
    
    public static final Integer ROLE_ADMIN = 1;
    public static final Integer ROLE_USER = 2;

	/*
	 * The method handle the user registration task.
	 * @param u the new user detail as User Object.
	 */
	
	public void register(AppUser u);
	
	 /*
     * The method handles login operation(authentication) using given credentials,
     * it returns User object when success and null when failed. 
     * When user account is blocked an exception will be thrown by this method.
	 * @param loginName
	 * @param password
	 * @return
	 * @throws com.hari.app.exception.UserBlockedException When user account is  blocked.
	 */
	public AppUser login(String loginName, String password)throws AppUserBlockedException;
	
	/**
     * Call this method to get list of registered users.
     * @return 
     */
    public List<AppUser> getUserList();
    
    /**
     * This method change the user login status for details passed as argument.
     * @param userId
     * @param loginStatus 
     */
    public void changeLoginStatus(Integer userId, Integer loginStatus);    
    
    /**
     * Check the username availability.
     * @param username
     * @return 
     */
    public Boolean isUsernameExist(String username);
}
