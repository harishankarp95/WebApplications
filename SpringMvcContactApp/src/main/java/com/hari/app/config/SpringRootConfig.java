package com.hari.app.config;
/**
*
* @author Harishankar
*/

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.hari.app.dao","com.hari.app.service"})
public class SpringRootConfig {
	//TODO: Services, DAO, DataSource, Email Sender or some other business layer beans
	
	@Bean
	public BasicDataSource getDataSource() {
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://192.168.8.210:5432/HSConAppTest");
		ds.setUsername("payroll");
		ds.setPassword("payroll");
		ds.setMaxTotal(2);
		ds.setInitialSize(1);
		ds.setTestOnBorrow(true);
		ds.setValidationQuery("SELECT 1");
		ds.setDefaultAutoCommit(true);
		
		return ds;
	}
}
