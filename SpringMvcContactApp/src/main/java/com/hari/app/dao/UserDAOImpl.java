package com.hari.app.dao;
/**
*
* @author Harishankar
*/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hari.app.domain.AppUser;
import com.hari.app.rm.AppUserRowMapper;

@Repository
public class UserDAOImpl extends BassDAO implements AppUserDAO{

	//@Override
	public void save(AppUser u) {

		String sql="INSERT INTO Appuser(name, phone,email, address, loginName, password, role, loginStatus)"
				+ "VALUES(:name, :phone, :email, :address, :loginName, :password, :role, :loginStatus)";

		Map m=new HashMap();
		m.put("name", u.getName());
		m.put("phone", u.getPhone());
		m.put("email", u.getEmail());
		m.put("address", u.getAddress());
		m.put("loginName", u.getLoginName());
		m.put("password", u.getPassword());
		m.put("role", u.getRole());
		m.put("loginStatus", u.getLoginStatus());
		try {
			KeyHolder kh = new GeneratedKeyHolder();
			SqlParameterSource ps = new MapSqlParameterSource(m);
			super.getNamedParameterJdbcTemplate().update(sql, ps, kh);
			Integer userId = kh.getKey().intValue();
			u.setUserId(userId);
		}catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
		}
		//List<Map<String,Object>> list = kh.getKeyList();

		/*String name = kh.getKey().toString();
        String phone = kh.getKey().toString();
        String email = kh.getKey().toString();
        String address = kh.getKey().toString();
        String loginName = kh.getKey().toString();
        String password = kh.getKey().toString();
        Integer role = kh.getKey().intValue();
        Integer loginStatus = kh.getKey().intValue();

        u.setName(name);
        u.setPhone(phone);
        u.setEmail(email);
        u.setAddress(address);
        u.setLoginName(loginName);
        u.setPassword(password);
        u.setRole(role);
        u.setLoginStatus(loginStatus);*/


	}

	public void update(AppUser u) {

		String sql="UPDATE Appuser "
				+ " SET name=:name,"
				+ " phone=:phone, "
				+ " email=:email,"
				+ " address=:address,"
				+ " role=:role,"
				+ " loginStatus=:loginStatus "
				+ " WHERE userId=:userId";

		Map m=new HashMap();
		m.put("name", u.getName());
		m.put("phone", u.getPhone());
		m.put("email", u.getEmail());
		m.put("address", u.getAddress());
		m.put("role", u.getRole());
		m.put("loginStatus", u.getLoginStatus());
		m.put("userId", u.getUserId());

		getNamedParameterJdbcTemplate().update(sql, m);

	}

	public void delete(AppUser u) {

		this.delete(u.getUserId());
	}

	public void delete(Integer userId) {

		String sql="DELETE FROM Appuser where userId=?";
		getJdbcTemplate().update(sql, userId);
	}

	//For find Single Record
	public AppUser findbyId(Integer userId) {

		String sql = "SELECT userId, name, phone, email, address, loginName, role, loginStatus"
				+ " FROM Appuser WHERE userId=?";
		AppUser u = getJdbcTemplate().queryForObject(sql, new AppUserRowMapper(),userId);

		return u;
	}

	public List<AppUser> findAll() {

		String sql = "SELECT userId, name, phone, email, address, loginName, role, loginStatus"
				+ " FROM Appuser";
		/*
           List<User> users = getJdbcTemplate().query(sql, new UserRowMapper());
           return users;
		 */
		return getJdbcTemplate().query(sql, new AppUserRowMapper());
	}

	public List<AppUser> findByProperty(String propName, Object propValue) {

		String sql = "SELECT userId, name, phone, email, address, loginName, role, loginStatus"
				+ " FROM Appuser WHERE "+propName+"=?";
		return getJdbcTemplate().query(sql, new AppUserRowMapper(), propValue);
	}


}
