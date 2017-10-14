package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.ContactMapper;
import com.atguigu.crm.dao.CustMapper;
import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CrmUtils;

@Service
public class CustService {

	@Autowired
	private CustMapper custMapper;
	@Autowired
	private ContactMapper contactMapper;
	
	@Transactional(readOnly=true)
	public Page<Customer> getPage(Page<Customer> page,Map<String,Object> conditions){
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		
		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		
		Map<String,Object> map = new HashMap<>();
		map.put("fromIndex", fromIndex);
		map.put("endIndex", endIndex);
		
		Map<String, Object> params = CrmUtils.changeParamsToMybatisParams(conditions);
		
		if(params!=null){
			map.putAll(params);
		}
		
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Object> entry = it.next();
			
			System.out.println(entry.getKey()+": "+entry.getValue());
		}
		
		page.setContent(custMapper.getContent(map));
		page.setTotalElements(custMapper.getTotalElements(map));
		
		return page;
	}
	
	@Transactional(readOnly=true)
	public Customer get(long id){
		Customer customer = custMapper.get(id);
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("fromIndex", 1);
		map.put("endIndex", 9999);
		
		List<Contact> list = contactMapper.getByCustomerId(map);
		Set<Contact> set = new HashSet<>(list);
		
		customer.setContacts(set);
		
		return customer;
	}
	
	@Transactional
	public void update(Customer customer){
		custMapper.update(customer);
	}
	
	@Transactional
	public void delete(long id){
		custMapper.delete(id);
	}
}
