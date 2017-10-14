package com.atguigu.crm.handler_jpa;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.service.jpa.DictService;
import com.atguigu.crm.utils.CrmUtils;

@Controller
@RequestMapping("/dict")
public class DictHandler {

	@Autowired
	private DictService dictService;
	
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
		
		Page<Dict> page = dictService.getPage(pageNo, pageSize, conditions);
		
		String params = CrmUtils.encodeParameterStringWithPrefix(conditions, "search_");
		
		map.put("page", page);
		map.put("params", params);
		
		return "dict/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String input(Map<String,Object> map){
		
		Dict dict = new Dict();
		map.put("dict", dict);
		
		return "dict/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String save(Dict dict){
		
		dictService.save(dict);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id")long id,Map<String,Object> map){
		
		System.out.println(id);
		
		Dict dict = dictService.get(id);
		
		System.out.println(dict);
		
		map.put("dict", dict);
		
		return "dict/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public String update(Dict dict){
		dictService.save(dict);
		
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")long id){
		dictService.delete(id);
		
		return "redirect:list";
	}
}
