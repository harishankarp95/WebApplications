package com.hari.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hari.csm.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Integer>{
	
	public Employee findByUsername(String username);

}
