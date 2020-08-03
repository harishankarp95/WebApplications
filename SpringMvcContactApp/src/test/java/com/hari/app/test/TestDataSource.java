package com.hari.app.test;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hari.app.config.SpringRootConfig;

public class TestDataSource {

	public static void main(String[] args) {
		
		ApplicationContext ctx=new AnnotationConfigApplicationContext(SpringRootConfig.class);
		DataSource ds=ctx.getBean(DataSource.class);
		JdbcTemplate jt=new JdbcTemplate(ds);
		String sql="INSERT INTO Appuser(name,phone,email,address,loginName,password) VALUES(?,?,?,?,?,?)";
		Object[] param = new Object[] {"Hari","8722280049","hari@gmail.com","odisha","harishankar","hari123"};
		jt.update(sql, param);
		System.out.println("-------------SQL Data Exicuted");
	}
}