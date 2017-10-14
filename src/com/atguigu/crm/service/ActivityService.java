package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.ActivityMapper;
import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.orm.Page;

@Service
public class ActivityService {

	@Autowired
	private ActivityMapper activityMapper;
	
	@Transactional
	public void save(CustomerActivity activity){
		activityMapper.save(activity);
	}
	
	@Transactional
	public void delete(long id){
		activityMapper.delete(id);
	}
	
	@Transactional
	public void update(CustomerActivity activity){
		activityMapper.update(activity);
	}
	
	@Transactional(readOnly=true)
	public CustomerActivity get(long id){
		return activityMapper.get(id);
	}
	
	public Page<CustomerActivity> getPage(Page<CustomerActivity> page,long id){
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		
		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		
		Map<String,Object> param = new HashMap<>();
		param.put("fromIndex", fromIndex);
		param.put("endIndex", endIndex);
		param.put("customerId", id);
		
		page.setContent(activityMapper.getByCustomerId(param));
		page.setTotalElements(activityMapper.getTotalElementsByCustomerId(id));
		
		return page;
	}
	
}
