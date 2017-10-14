package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.DictsMapper;

@Service
public class DictsService {

	@Autowired
	private DictsMapper dictsMapper;
	
	@Transactional(readOnly=true)
	public List<String> getRegion(){
		return dictsMapper.getResions();
	}
	
	@Transactional(readOnly=true)
	public List<String> getLevels(){
		return dictsMapper.getLevels();
	}
	@Transactional(readOnly=true)
	public List<String> getSatify(){
		return dictsMapper.getSatify();
	}
	@Transactional(readOnly=true)
	public List<String> getCredit(){
		return dictsMapper.getCredit();
	}
	@Transactional(readOnly=true)
	public List<String> getServe(){
		return dictsMapper.getServe();
	}
}
