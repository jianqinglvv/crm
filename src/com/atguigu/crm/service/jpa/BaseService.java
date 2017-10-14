package com.atguigu.crm.service.jpa;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.atguigu.crm.baseRepository.BaseRepository;
import com.atguigu.crm.utils.CrmUtils;


public class BaseService<T>{
	
	@Autowired
	protected BaseRepository<T> baseRepository;
	
	
	public Page<T> getPage(int pageNo, int pageSize, Map<String, Object> conditions){
		Sort sort = new Sort("id");
		PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize,sort);

		Specification<T> specification = CrmUtils.parseConditionsToSpecification(conditions);
		
		return baseRepository.findAll(specification, pageRequest);
	}

	public void save(T t) {
		baseRepository.save(t);
		
	}

	public T get(long id) {
//		baseRepository.getOne(id);
		T t = baseRepository.findOne(id);
		return t;
	}
	
	public void update(T t){
		baseRepository.saveAndFlush(t);
	}

	public void delete(long id) {

		baseRepository.delete(id);
		
	}
}
