package com.atguigu.crm.service.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.repository.DictRepository;
import com.atguigu.crm.utils.CrmUtils;

@Service
public class DictService extends BaseService<Dict> {
/*
	@Autowired
	private DictRepository dictRepository;
	
	public Page<Dict> getPage(int pageNo, int pageSize, Map<String, Object> conditions){
		PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);

		Specification<Dict> specification = CrmUtils.parseConditionsToSpecification(conditions);
		
		return dictRepository.findAll(specification, pageRequest);
	}

	public void save(Dict dict) {
		dictRepository.save(dict);
		
	}

	public Dict get(long id) {
//		dictRepository.getOne(id);
		Dict dict = dictRepository.findOne(id);
		return dict;
	}
	
	public void update(Dict dict){
		dictRepository.saveAndFlush(dict);
	}

	public void delete(long id) {

		dictRepository.delete(id);
	}
*/	
}
