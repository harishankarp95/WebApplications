package com.hari.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hari.model.Emp;

public class EmpDao {

	JdbcTemplate jdbcTemplate;
	
	Logger logger = Logger.getLogger(EmpDao.class.getName());
	
	public void setTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public List<Emp> getEmployeeByPage(int id, int total)
	{
		logger.info("Inside EmpDao...page id:- "+id+"....Total..."+total);
		
		String sql="SELECT * FROM Employee LIMIT "+total+" OFFSET "+id;

		 return jdbcTemplate.query(sql,new RowMapper<Emp>(){    
		        public Emp mapRow(ResultSet rs, int row) throws SQLException {    
		            Emp e=new Emp();    
		            e.setId(rs.getInt(1));    
		            e.setName(rs.getString(2));    
		            e.setSalary(rs.getFloat(3));    
		            return e;    
		        }    
		    });    

	}
}
