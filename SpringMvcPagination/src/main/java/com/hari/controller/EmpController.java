package com.hari.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hari.dao.EmpDao;
import com.hari.model.Emp;

@Controller
public class EmpController {

	Logger logger = Logger.getLogger(EmpController.class.getName());
	
	@Autowired
	EmpDao dao;
	
	@RequestMapping(value="/viewemp/{pageid}")
	public String edit(@PathVariable int pageid,Model m)
	{
		logger.info("Inside Controller method");
		int total=5;
		int id;
		if(pageid == 1)
		{
			
		}
		else {
			pageid=(pageid - 1) * total+1;
		}
		logger.info("Page ID:- "+pageid);
		id=pageid-1;
		List<Emp> list=dao.getEmployeeByPage(id,total);
		m.addAttribute("msg", list);
		
		return"viewemp";
	}
}
