package com.atguigu.crm.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.repository.StorageRepository;
import com.atguigu.crm.utils.CrmUtils;

@Service
public class StorageService {

	@Autowired
	private StorageRepository storageRepository;

	public Page<Storage> getPage(int pageNo, int pageSize, Map<String, Object> conditions) {
		PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);

		Specification<Storage> specification = CrmUtils.parseConditionsToSpecification(conditions);
		
//		List<Storage> list = storageRepository.findAll(specification, pageRequest).getContent();
//		long count = storageRepository.count(specification);
//		PageImpl<Storage> page = new PageImpl<>(list, pageRequest, count);
//
//		return page;
		
		Page<Storage> all = storageRepository.findAll(specification, pageRequest);
		
		
		return all;
	}

	
}
