package com.hari.app.service;
/**
*
* @author Harishankar
*/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.hari.app.dao.AppUserDAO;
import com.hari.app.dao.BassDAO;
import com.hari.app.domain.AppUser;
import com.hari.app.exception.AppUserBlockedException;
import com.hari.app.rm.AppUserRowMapper;

@Service
public class AppUserServiceImpl extends BassDAO implements AppUserService{

	@Autowired
	private AppUserDAO userDAO;

	public void register(AppUser u) {


		userDAO.save(u);
	}

	public AppUser login(String loginName, String password) throws AppUserBlockedException {

		String sql = "SELECT userId, name, phone, email, address, role, loginStatus, loginName"
				+ " FROM Appuser WHERE loginName=:ln AND password=:pw";
		Map m = new HashMap();
		m.put("ln", loginName);
		m.put("pw", password);
		try {
			AppUser u = getNamedParameterJdbcTemplate().queryForObject(sql, m, new AppUserRowMapper());
			if (u.getLoginStatus().equals(AppUserService.LOGIN_STATUS_BLOCKED)) {
				throw new AppUserBlockedException("Your accout has been blocked. Contact to admin.");
			} else {
				return u;
			}
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}

	}

	public List<AppUser> getUserList() {

		return userDAO.findByProperty("role", AppUserService.ROLE_USER);
	}

	public void changeLoginStatus(Integer userId, Integer loginStatus) {

		String sql = "UPDATE Appuser SET loginStatus=:lst WHERE userId=:uid";
		Map m = new HashMap();
		m.put("uid", userId);
		m.put("lst", loginStatus);
		getNamedParameterJdbcTemplate().update(sql, m);
	}

	public Boolean isUsernameExist(String username) {

		String sql = "SELECT count(loginName) FROM Appuser WHERE loginName=?";
        Integer count = getJdbcTemplate().queryForObject(sql, new String[]{username}, Integer.class);
        if(count==1){
            return true;
        }else{
            return false;
        }
	}


}
