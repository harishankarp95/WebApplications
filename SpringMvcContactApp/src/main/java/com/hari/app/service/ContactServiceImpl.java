package com.hari.app.service;
/**
*
* @author Harishankar
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hari.app.dao.BassDAO;
import com.hari.app.dao.ContactDAO;
import com.hari.app.domain.Contact;
import com.hari.app.rm.ContactRowMapper;
import com.hari.app.util.StringUtil;

@Service
public class ContactServiceImpl extends BassDAO implements ContactService{

	@Autowired
	private ContactDAO contactDAO;
	
	public void save(Contact c) {

		contactDAO.save(c);
	}

	public void update(Contact c) {

		contactDAO.update(c);
	}

	public void delete(Integer contactId) {

		contactDAO.delete(contactId);
	}

	public void delete(Integer[] contactIds) {

		 String ids = StringUtil.toCommaSeparatedString(contactIds);
	        String sql = "DELETE FROM Contact WHERE contactId IN("+ids+")";
	        getJdbcTemplate().update(sql);
	}

	public Contact findById(Integer contactId) {

		 return contactDAO.findById(contactId);
	}

	public List<Contact> findUserContact(Integer userId) {
		
		return contactDAO.findByProperty("userId", userId);
	}

	public List<Contact> findUserContact(Integer userId, String txt) {
		
		String sql = "SELECT contactId, userId, name, phone, email, address, remark FROM contact WHERE userId=? AND (name LIKE '%"+txt+"%' OR address LIKE '%"+txt+"%' OR phone LIKE '%"+txt+"%' OR email LIKE '%"+txt+"%' OR remark LIKE '%"+txt+"%')";
        return getJdbcTemplate().query(sql, new ContactRowMapper(),userId); 
	}

	
}
