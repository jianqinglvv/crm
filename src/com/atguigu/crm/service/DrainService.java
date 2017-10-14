package com.atguigu.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.DrainMapper;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.CrmUtils;

@Service
public class DrainService {

	@Autowired
	private DrainMapper drainMapper;
	
	@Autowired
	private CustService custService;
	
	
	@Transactional
	public void callDrainProcedure(){
		System.out.println("调用存储过程");
		drainMapper.callDrainProcedure();
	}
	
	@Transactional(readOnly=true)
	public Page<CustomerDrain> getPage(Page<CustomerDrain> page,Map<String,Object> params){
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
		
		int totalElements = drainMapper.getTotalElements(map);
		List<CustomerDrain> list = drainMapper.getDrains(map);
		
		page.setTotalElements(totalElements);
		page.setContent(list);
		return page;
	}
	
	@Transactional(readOnly=true)
	public CustomerDrain get(long id){
		return drainMapper.get(id);
	}
	
	@Transactional
	public void update(CustomerDrain drain){
//		if(drain.getReason()!=null){
//			drain.setDrainDate(new Date());
//			Long customerId = drain.getCustomer().getId();
//			
//			Customer customer = custService.get(customerId);
//			customer.setState("流失");
//			
//			custService.update(customer);
//		}
		
		drainMapper.update(drain);
	}
	
	@Transactional
	public void confirm(CustomerDrain drain){
		
		drain.setDrainDate(new Date());
		drain.setStatus("流失");
		
		Long customerId = drain.getCustomer().getId();
		
		Customer customer = custService.get(customerId);
		customer.setState("流失");
		
		custService.update(customer);
		
		drainMapper.confirm(drain);
	}
}
