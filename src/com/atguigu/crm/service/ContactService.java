package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.ContactMapper;
import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.orm.Page;

@Service
public class ContactService {

	@Autowired
	private ContactMapper contactMapper;
	
	
	@Transactional(readOnly=true)
	public Page<Contact> getPage(Page<Contact> page,long id){
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		
		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		
		Map<String,Object> param = new HashMap<>();
		param.put("fromIndex", fromIndex);
		param.put("endIndex", endIndex);
		param.put("id", id);
		
		List<Contact> list = contactMapper.getByCustomerId(param);
		
		page.setContent(list);
		page.setTotalElements(contactMapper.getTotalElementsByCustomerId(id));
		
		return page;
	}
	
	@Transactional
	public void save(Contact contact){
		contactMapper.save(contact);
	}
	@Transactional(readOnly=true)
	public List<Contact> getByCustomerId(Map<String,Object> map){
		return contactMapper.getByCustomerId(map);
	}
	@Transactional
	public void update(Contact contact){
		contactMapper.update(contact);
	}
	@Transactional
	public void delete(long id){
		contactMapper.delete(id);
	}
	@Transactional(readOnly=true)
	public Contact get(long id){
		return contactMapper.get(id);
	}
	@Transactional(readOnly=true)
	int getTotalElementsByCustomerId(long id){
		return contactMapper.getTotalElementsByCustomerId(id);
	}
}
