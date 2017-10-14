package com.atguigu.crm.handler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustService;
import com.atguigu.crm.service.DictsService;
import com.atguigu.crm.utils.CrmUtils;

@Controller
@RequestMapping("/customer")
public class CustomerHandler {
	
	@Autowired
	private CustService custService;
	
	@Autowired
	private DictsService dictsService;

	@ResponseBody
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id")long id){
		
		custService.delete(id);
		
		return "1";
	}
	
	
	
	
	@RequestMapping(value="/list")
	public String list(@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNoStr,
								@RequestParam(value="pageSize",required=false,defaultValue="4")String pageSizeStr,
								HttpServletRequest request,
								Map<String,Object> map){
		
		int pageNo = 1;
		int pageSize = 4;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (Exception e) {	}
		
		Page<Customer> page = new Page<>();
		
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		Map<String, Object> conditions = WebUtils.getParametersStartingWith(request,"search_");
		
		String params = CrmUtils.encodeParameterStringWithPrefix(conditions, "search_");
		System.out.println(params);
		
		page = custService.getPage(page, conditions);

		map.put("page", page);
		map.put("params",params);
		
		List<String> levels = dictsService.getLevels();
		
		List<String> regions = dictsService.getRegion();
		
		map.put("levels", levels);
		map.put("regions", regions);
		
		return "customer/list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id")long id,Map<String,Object> map){
		Customer customer = custService.get(id);
		
		map.put("customer", customer);
		
		List<String> levels = dictsService.getLevels();
		List<String> credits = dictsService.getCredit();
		List<String> regions = dictsService.getRegion();
		List<String> satify = dictsService.getSatify();
		
		map.put("levels", turnListToMap(levels));
		map.put("regions",  turnListToMap(regions));
		map.put("credits",  turnListToMap(credits));
		map.put("satify",  turnListToMap(satify));
		
		
		return "customer/input";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(Customer customer){
		
		custService.update(customer);
		
		return "redirect:list";
		//return "redirect:/customer/list";
	}
	
	
	
	
	
	
	private Map<String,String> turnListToMap(List<String> list){
		Map<String,String> map = new LinkedHashMap<>();

		for (String string : list) {
			map.put(string, string);
		}
		
		return map;
	}
}
