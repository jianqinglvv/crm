package com.atguigu.crm.handler;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;
import com.atguigu.crm.service.SalesPlanService;

@Controller
@RequestMapping(value="/plan")
public class SalesPlanHandler {

	@Autowired
	private SalesPlanService salesPlanService;
	
	@Autowired
	private SalesChanceService salesChanceService;
	
	@RequestMapping(value="execute/{chanceId}",method=RequestMethod.GET)
	public String execute(@PathVariable("chanceId")int chanceId,Map<String,Object> map){
		SalesChance chance = salesChanceService.get(chanceId);
		List<SalesPlan> plans = salesPlanService.getPlans(chanceId);
		
		map.put("chance", chance);
		map.put("plans", plans);
		
		return "plan/exec";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/make")
	public SalesPlan make(SalesPlan plan){
		salesPlanService.save(plan);
		return plan;
	}
	@RequestMapping(value="/make/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("id")int id){
		salesPlanService.delete(id);
	}

	@ResponseBody
	@RequestMapping(value="/make/{id}",method=RequestMethod.PUT)
	public SalesPlan update(SalesPlan plan){
		salesPlanService.update(plan);
		return plan;
	}

	
	@RequestMapping(value="/make/{id}",method=RequestMethod.GET)
	public String make(@PathVariable("id")int id,Map<String,Object> map){
		
		SalesChance chance = salesChanceService.get(id);
		
		List<SalesPlan> plans = salesPlanService.getPlans(id);
		
		chance.setSalesPlans(new HashSet<SalesPlan>(plans));
		map.put("chance", chance);
		map.put("plans", plans);
		
		return "/plan/make";
	}
	
//	@RequestMapping(value="/chance/list")
	@RequestMapping(value="/list")
	public String listChance(@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNoStr,
							 @RequestParam(value="pageSize",required=false,defaultValue="4")String pageSizeStr,
							 HttpSession session,HttpServletRequest request,Map<String,Object> map){
		int pageNo = 1;
		int pageSize = 4;
		Page<SalesChance> page = new Page<>();
		
		User user = (User) session.getAttribute("user");
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		if(user != null){
			Long designeeId = user.getId();
			 Map<String,Object> conditions = WebUtils.getParametersStartingWith(request,"search_");
//			conditions.put("designeeId", designeeId);
			conditions.put("NE_status", 1);
			conditions.put("EQ_designeeId", designeeId);
			
			salesChanceService.getPage(page,conditions);
			
			String params = encodeParameterStringWithPrefix(conditions,"search_");
			
			map.put("params", params);
			
			map.put("page", page);
			
		}
		return "/plan/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@RequestMapping(value="/chance/list", method = RequestMethod.GET)
	public String getPage1(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageSize", required = false, defaultValue = "4") String pageSizeStr,
			Map<String, Object> map, HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException {
		int pageNo = 1;
		int pageSize = 4;
		Page<SalesChance> page = new Page<>();
		
		User user = (User) session.getAttribute("user");
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		if(user != null){
			Long designeeId = user.getId();
			 Map<String,Object> conditions = WebUtils.getParametersStartingWith(request,"search_");
//			conditions.put("designeeId", designeeId);
			conditions.put("NE_status", 1);
			conditions.put("EQ_designeeId", designeeId);
			
			//解决get方式地址栏中文乱码
			for (Map.Entry<String, Object> entry : conditions.entrySet()) {
				String key = entry.getKey();
				
				String value = new String((entry.getValue()+"").getBytes("ISO-8859-1"), "utf-8");

				System.out.println(key + " :" + value);

				conditions.put(key, value);
			}
			
			salesChanceService.getPage(page,conditions);
			
			String params = encodeParameterStringWithPrefix(conditions,"search_");
			
			map.put("params", params);
			
			map.put("page", page);
			
		}
		return "/plan/list";
	

	}
	
	
	
	
	
	
	public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix) {
		if ((params == null) || (params.size() == 0)) {
			return "";
		}

		if (prefix == null) {
			prefix = "";
		}

		StringBuilder queryStringBuilder = new StringBuilder();
		Iterator<Entry<String, Object>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			queryStringBuilder.append(prefix).append(entry.getKey()).append('=').append(entry.getValue());
			if (it.hasNext()) {
				queryStringBuilder.append('&');
			}
		}
		return queryStringBuilder.toString();
	}
}
