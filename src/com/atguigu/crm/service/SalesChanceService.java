package com.atguigu.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.ContactMapper;
import com.atguigu.crm.dao.CustMapper;
import com.atguigu.crm.dao.SalesChanceMapper;
import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CrmUtils;

@Service
public class SalesChanceService {

	@Autowired
	private SalesChanceMapper salesChanceMapper;
	@Autowired
	private ContactMapper contactMapper;
	@Autowired
	private CustMapper custMapper;
	
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(Page<SalesChance> page){
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		
		int fromIndex = (pageNo-1)*pageSize + 1;
		int endIndex = fromIndex + pageSize;
		
		Map<String, Object> map = new HashMap<>();
		map.put("fromIndex", fromIndex);
		map.put("endIndex", endIndex);
		
		int totalElements = salesChanceMapper.getTotalElements(map);
		List<SalesChance> list = salesChanceMapper.getContent(map);
		
		page.setTotalElements(totalElements);
		page.setContent(list);
		
		return page;
	}

	@Transactional
	public void save(SalesChance chance) {
		chance.setCreateDate(new Date());
		chance.setStatus(1);
		salesChanceMapper.save(chance);
	}
	
	@Transactional
	public void delete(int id){
		salesChanceMapper.delete(id);
	}
	
	@Transactional(readOnly=true)
	public SalesChance get(int id){
		return salesChanceMapper.get(id);
	}
	
	@Transactional
	public void update(SalesChance chance){
		salesChanceMapper.update(chance);
	}
	
	@Transactional
	public void dispatch(SalesChance chance){
//		chance.setStatus(2);
		salesChanceMapper.dispatch(chance);
//		salesChanceMapper.update(chance);
	}
	
//	@Transactional(readOnly=true)
//	public Page<SalesChance> getByDesigneeId(Page<SalesChance> page, Map<String, Object> conditions) {
//		int pageNo = page.getPageNo();
//		int pageSize = page.getPageSize();
//		
//		int fromIndex = (pageNo-1)*pageSize + 1;
//		int endIndex = fromIndex + pageSize;
//		
//		Map<String,Object> map = new HashMap<>();
//		map.put("fromIndex", fromIndex);
//		map.put("endIndex", endIndex);
//
//		
////		
//		Map<String, Object> mybatisParam = changeParamsToMybatisParams(conditions);
//		
//		if(mybatisParam!=null){
//			map.putAll(mybatisParam);
//		}
//		
//		page.setContent(salesChanceMapper.getContent(map));
//		page.setTotalElements(salesChanceMapper.getTotalElements(map));
//		//补充查询条件
//		
//		
//		return page;
//	}
	
	
	
	
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(Page<SalesChance> page, Map<String, Object> params) {
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		
		int fromIndex = (pageNo-1)*pageSize + 1;
		int endIndex = fromIndex + pageSize;
		
		Map<String, Object> map = new HashMap<>();
		map.put("fromIndex", fromIndex);
		map.put("endIndex", endIndex);
		
		//LIKE_custName  LIKE_title  LIKE_contact
		Map<String, Object> mybatisParam = CrmUtils.changeParamsToMybatisParams(params);
		if(mybatisParam!=null){
			
			map.putAll(mybatisParam);
		}
		
		int totalElements = salesChanceMapper.getTotalElements(map);
		List<SalesChance> list = salesChanceMapper.getContent(map);
		
		page.setTotalElements(totalElements);
		page.setContent(list);
		
		return page;
	}

	

	@Transactional
	public void finish(int chanceId) {
		SalesChance chance = get(chanceId);
		chance.setStatus(3);
		update(chance);
		
		Customer customer = new Customer();
		customer.setName(chance.getCustName());
		customer.setNo(UUID.randomUUID().toString());
		customer.setState("正常");
		
		custMapper.save(customer);
		
		System.out.println(customer.getId());
		
		Contact contact = new Contact();
		contact.setName(chance.getContact());
		contact.setTel(chance.getContactTel());
		contact.setCustomer(customer);
		
		contactMapper.save(contact );
		
	}
	@Transactional	
	public void fail(int chanceId) {
		SalesChance chance = get(chanceId);
		chance.setStatus(4);
		update(chance);
	}


	
}
