package com.atguigu.crm.handler_jpa;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.service.jpa.AuthorityService;
import com.atguigu.crm.service.jpa.RoleService;
import com.atguigu.crm.utils.CrmUtils;

@Controller
@RequestMapping("/role")
public class RoleHandler {

	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNoStr,
				@RequestParam(value="pageSize",required=false,defaultValue="4")String pageSizeStr,
				HttpServletRequest request,Map<String,Object> map){
		
		Map<String, Object> conditions = WebUtils.getParametersStartingWith(request, "search_");
		
		int pageNo = 1;
		int pageSize = 4;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (Exception e) {}
//		System.out.println("pageNo============="+pageNo);
		
		Page<Role> page = roleService.getPage(pageNo, pageSize, conditions);
		
		String params = CrmUtils.encodeParameterStringWithPrefix(conditions, "search_");
		
		map.put("page", page);
		map.put("params", params);
		
		return "role/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String input(Map<String,Object> map){
		
		Role role = new Role();
		map.put("role", role);
		
		return "role/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String save(Role role){
		
		roleService.save(role);
		
		return "redirect:list";
	}
	

	@RequestMapping(value="/assign/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id")long id,Map<String,Object> map){
		System.out.println(id);
		
		Role role = roleService.get(id);
		List<Authority> authorities = role.getAuthorities();
		Set<Authority> parentAuthorities = new LinkedHashSet<>(authorityService.getParentAuthorities());
		List<Authority> childAuthorities = new ArrayList<>(authorityService.getChildAuthorities());
		
		map.put("parentAuthorities", parentAuthorities);
		map.put("childAuthorities", childAuthorities);
		
		System.out.println(role);
		System.out.println(parentAuthorities.size());
		System.out.println(childAuthorities.size());
		
		map.put("role", role);
		
//		map.put("id", id);
		
		return "role/assign";
	}
	
	@RequestMapping(value="/assign/{id}",method=RequestMethod.PUT)
	public String update(@PathVariable("id")long roleId,Role role){
		List<String> authorities2 = role.getAuthorities2();
		
		role = roleService.get(roleId);
		role.setAuthorities2(authorities2);
		
//		System.out.println(role.getAuthorities2().size());
		roleService.update(role);
		
		return "redirect:/role/list";
	}
	/*
	@RequestMapping(value="/assign/",method=RequestMethod.PUT)
	public String update2(@PathVariable("id")long id,List<String> authoritiesId){
		Role role = roleService.get(id);
		
		role.setAuthorities2(authoritiesId);
		
		for (String string : authoritiesId) {
			System.out.println(string);
		}
		
		return "redirect:list";
	}
	*/
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id")long id){
		try {
			roleService.delete(id);
		} catch (Exception e) {
			return "redirect:/role/list";
		}
		
		return "redirect:/role/list";
	}
	
}
