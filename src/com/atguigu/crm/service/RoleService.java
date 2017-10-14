package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.RoleMapper;
import com.atguigu.crm.entity.Role;

@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Transactional(readOnly=true)
	public List<Role> getRoles(){
		return roleMapper.getRoles();
	}
}
