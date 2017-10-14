package com.atguigu.crm.service.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Authority;
@Service
public class AuthorityService extends BaseService<Authority>{

	public List<Authority> getParentAuthorities(){
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("EQ_url","/" );
		
		Page<Authority> page = getPage(1, 999, conditions);
		
		return page.getContent();
	}
	
	public List<Authority> getChildAuthorities(){
		Map<String, Object> conditions = new HashMap<>();

		conditions.put("NOTEQ_url","/" );
		
		Page<Authority> page = getPage(1, 999, conditions);
		
		return page.getContent();
	}
}
