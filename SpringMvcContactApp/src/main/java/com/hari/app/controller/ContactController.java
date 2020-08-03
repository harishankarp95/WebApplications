package com.hari.app.controller;
/**
*
* @author Harishankar
*/

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hari.app.domain.Contact;
import com.hari.app.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	ContactService contactservice;

	@RequestMapping(value = "/user/contact_form")
	public String contactForm(Model m)
	{
		Contact contact = new Contact();
		m.addAttribute("command", contact);

		return "contact_form"; //JSp Page
	}

	@RequestMapping(value = "/user/edit_contact")
	public String prepareEditForm(Model m, HttpSession session, @RequestParam("cid") Integer contactId)
	{
		session.setAttribute("aContactId", contactId);
		Contact c = contactservice.findById(contactId);
		m.addAttribute("command", c);

		return "contact_form"; //JSp Page
	}
	
	@RequestMapping(value = "/user/save_contact")
	public String saveOrUpdateContact(@ModelAttribute("command") Contact c, Model m, HttpSession session)
	{
		Integer contactId = (Integer) session.getAttribute("aContactId");
		
		if (contactId == null) {
			// save Contact
			try {

				Integer userId = (Integer) session.getAttribute("userId");
				c.setUserId(userId); //FK - logged in userId

				contactservice.save(c);
				return"redirect:clist?act=sv"; //redirect User to contact list url

			}catch (Exception e) {
				e.printStackTrace();
				m.addAttribute("err", "Failed to save contact");
				return "contact_form";
			}
		} else {
				//Update Contact
			try {
				c.setContactId(contactId);
				contactservice.update(c);
				return"redirect:clist?act=ed"; //redirect User to contact list url

			}catch (Exception e) {
				e.printStackTrace();
				m.addAttribute("err", "Failed to Edit contact Record");
				return "contact_form";
			}
		}
	}
	
	@RequestMapping(value = "/user/clist")
	public String contactList(Model m, HttpSession session)
	{
		Integer userId = (Integer) session.getAttribute("userId");
		m.addAttribute("contactList", contactservice.findUserContact(userId));
		
		return "clist"; //JSp Page
	}
	
	@RequestMapping(value = "/user/contact_search")
	public String contactSearch(Model m, HttpSession session, @RequestParam("freeText") String freeText)
	{
		Integer userId = (Integer) session.getAttribute("userId");
		m.addAttribute("contactList", contactservice.findUserContact(userId, freeText));
		
		return "clist"; //JSp Page
	}
	
	@RequestMapping(value = "/user/del_contact")
	public String deleteContact(@RequestParam("cid") Integer contactId)
	{
		contactservice.delete(contactId);
		return "redirect:clist?act=del"; 
	}
	
	@RequestMapping(value = "/user/bulk_cdelete")
	public String deleteBulkContact(@RequestParam("cid") Integer[] contactIds)
	{
		contactservice.delete(contactIds);
		return "redirect:clist?act=del"; 
	}
}
