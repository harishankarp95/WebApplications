package com.hari.app.dao;

import java.util.List;

import com.hari.app.domain.AppUser;

public interface AppUserDAO {

	public void save(AppUser u);
	
	public void update(AppUser u);
	
	public void delete(AppUser u);
	
	public void delete(Integer userId);
	
	public AppUser findbyId(Integer userId);
	
	public List<AppUser> findAll();
	
	public List<AppUser> findByProperty(String propName, Object propValue);
}
