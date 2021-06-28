package com.hari.csm.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hari.csm.entity.Employee;
import com.hari.csm.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		Employee employee  =new Employee();
		model.addAttribute("employee", employee);
		return"employeeSignUp";
	}
	
	
	
	@PostMapping("/register")
	public String registerEmployee(@ModelAttribute Employee employee,Model model) {
		
		Employee employee2 = service.registerEmployee(employee);
		System.out.println(employee2);
		if (employee2!=null) {
			model.addAttribute("employee", "Employee registration successfully done");
		}else {
			model.addAttribute("employee", "Data not inserted");
		}
		return "index";
	}
	
	@GetMapping("/signin")
	public String signin(Model model) {
		return "empSignIn";
	}
	
	@PostMapping("/empSignin")
	public String empSignin(@RequestParam("username") String username, @RequestParam("password")String password, Model model) { 
		Employee employee =service.signIn(username);
		if (employee!=null) {
			if(employee.getPassword().equals(password)) {
				model.addAttribute("empSignIn","Employee SignIn successfully Done");
				model.addAttribute("employee", employee);
			}else {
				model.addAttribute("empSignIn","SignIn failed");
			}
		}
		
		return "signInSuccess";
	  }
	@GetMapping("/showUpdate/{id}")
	public String updateEmployee(@PathVariable("id") int id,Model model) {
		Employee employee  =service.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return"employeeUpdate";
	}
	@PostMapping(value = "/showUpdate/{id}")
	public String updateEmployee(@ModelAttribute Employee employee,@PathVariable("id") int id,Model model) {
		Employee existingEmp = service.getEmployeeById(id);
		existingEmp.setName(employee.getName());
		existingEmp.setEmail(employee.getEmail());
		existingEmp.setPhone(employee.getPhone());
		Employee employee2 = service.registerEmployee(existingEmp);
		System.out.println(employee2);
		if (employee2!=null) {
			model.addAttribute("empSignIn", "Employee Update successfully done");
		}else {
			model.addAttribute("empSignIn", "Data not updated");
		}
		return"signInSuccess";
	}
	@GetMapping("/uploadPic/{id}")
	public String updatePhoto(@PathVariable("id") int id,Model model) {
		model.addAttribute("id", id);
		return "profilePic";
	}
	
	@PostMapping("/uploadPic/{id}")
	public String updatePhotoSave(@PathVariable("id") int id,@RequestParam("image") MultipartFile image,Model model) throws IOException {
		//String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        Employee employee = service.getEmployeeById(id);
        employee.setPic(image.getBytes());
        
        service.registerEmployee(employee);
        model.addAttribute("empSignIn","Profile photo uploaded successfully....!!!!");
        model.addAttribute("employee", employee);
		return "signInSuccess";
	}
	 @GetMapping("/deleteEmployee/{id}")
	 public String deleteEmp(@PathVariable("id") int id, Model model) {
		 service.deleteEmployee(id);
		 model.addAttribute("employee", "Employee Profile SuccessFully Deleted..");
		 return "index";
	 }
}
