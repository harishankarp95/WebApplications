package com.hari.app.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

//Note:- Do not use @Repositry, @Service, @Component annotation
abstract public class BassDAO extends NamedParameterJdbcDaoSupport{

	@Autowired
	public void setDataSource2(DataSource ds) {
		super.setDataSource(ds);
	}
}
