package com.hari.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hari.csm.entity.Employee;
import com.hari.csm.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;

	public Employee registerEmployee(Employee employee) {
		return repository.save(employee);
	}

	public Employee signIn(String username) {
		return repository.findByUsername(username);
	}
	
	public Employee getEmployeeById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public void deleteEmployee(int id) {
		
		repository.deleteById(id);
	}
}
