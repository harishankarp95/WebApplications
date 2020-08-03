package com.hari.app.rm;
/**
*
* @author Harishankar
*/

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hari.app.domain.AppUser;

public class AppUserRowMapper implements RowMapper<AppUser>{

	public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {

		AppUser u = new AppUser();
		u.setUserId(rs.getInt("userId"));
		u.setName(rs.getString("name"));
		u.setEmail(rs.getString("email"));
		u.setPhone(rs.getString("phone"));
		u.setAddress(rs.getString("address"));
		u.setLoginName(rs.getString("loginName"));
		u.setRole(rs.getInt("role"));
		u.setLoginStatus(rs.getInt("loginStatus"));

		return u;
	}



}
