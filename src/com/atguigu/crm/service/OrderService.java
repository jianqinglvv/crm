package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.OrderMapper;
import com.atguigu.crm.entity.Order;
import com.atguigu.crm.orm.Page;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Transactional(readOnly=true)
	public Order get(long id){
		return orderMapper.get(id);
	}
	
	@Transactional(readOnly=true)
	public int getTotalElementsByCustomerId(long customerId){
		return orderMapper.getTotalElementsByCustomerId(customerId);
	}
	
	@Transactional(readOnly=true)
	public Page<Order> getPage(Page<Order> page,long id){
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		
		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		
		Map<String,Object> param = new HashMap<>();
		param.put("fromIndex", fromIndex);
		param.put("endIndex", endIndex);
		param.put("customerId", id);
		
		List<Order> list = orderMapper.getByCustomerId(param);
		
		page.setContent(list);
		page.setTotalElements(orderMapper.getTotalElementsByCustomerId(id));
		return page;
	}
}
