package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.SalesPlanMapper;
import com.atguigu.crm.entity.SalesPlan;

import oracle.net.aso.s;

@Service
public class SalesPlanService {

	@Autowired
	private SalesPlanMapper salesPlanMapper;
	
	@Transactional(readOnly=true)
	public List<SalesPlan> getPlans(int chanceId){
		Map<String,Object> map = new HashMap<>();
		map.put("chanceId", chanceId);
		return salesPlanMapper.getContent(map);
	}
	
	@Transactional
	public void save(SalesPlan plan){
		salesPlanMapper.save(plan);
	}
	@Transactional
	public void delete(int id){
		salesPlanMapper.delete(id);
	}
	@Transactional
	public void update(SalesPlan plan){
		salesPlanMapper.update(plan);
	}
}
