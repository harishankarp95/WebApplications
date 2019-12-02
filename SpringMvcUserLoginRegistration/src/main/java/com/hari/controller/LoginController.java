package com.hari.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hari.model.Login;
import com.hari.model.User;
import com.hari.service.UserService;



@Controller
public class LoginController {

	@Autowired
	public UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("Inside showLogin()......");
		ModelAndView modelAndView=new ModelAndView("login");
		modelAndView.addObject("login", new Login());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	      @ModelAttribute("login") Login login) {
		System.out.println("Inside loginProcess() method..");
	    ModelAndView mav = null;
 
		User user = userService.validateUser(login);
		
		if(null != user)
		{
			HttpSession session=request.getSession();
			session.setAttribute("name", user.getFirstname());
			mav = new ModelAndView("welcome");
			mav.addObject("firstname", user.getFirstname());
		}else {
			mav = new ModelAndView("login");
		    mav.addObject("message", "Username or Password is wrong!!");
		}
		
		return mav;
	}
}
