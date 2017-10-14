package com.atguigu.crm.handler_jpa;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.service.jpa.ReportService;
import com.atguigu.crm.utils.CrmUtils;

@Controller
@RequestMapping("/report")
public class ReportHandler {

	@Autowired
	private ReportService reportService;
	
	@RequestMapping("/consist")
	public String consist(@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNoStr,
				@RequestParam(value="pageSize",required=false,defaultValue="4")String pageSizeStr,
				HttpServletRequest request,Map<String,Object> map){
		
		Map<String, Object> conditions = WebUtils.getParametersStartingWith(request, "search_");
		
		int pageNo = 1;
		int pageSize = 4;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (Exception e) {}
		
		
		
		Page<Object[]> page = null;
			page = reportService.getConsist(pageNo, pageSize, conditions);
		
		String params = CrmUtils.encodeParameterStringWithPrefix(conditions, "search_");
		
		Object object = conditions.get("type");
		if(object==null || (String)object == ""){
			object = "level"; 
		}
		
		map.put("type", object);
		map.put("page", page);
		map.put("params", params);
		
		return "report/consist";
	}
	
	
	@RequestMapping("/pay")
	public String pay(@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNoStr,
				@RequestParam(value="pageSize",required=false,defaultValue="4")String pageSizeStr,
				HttpServletRequest request,Map<String,Object> map){
		
		Map<String, Object> conditions = WebUtils.getParametersStartingWith(request, "search_");
		
		int pageNo = 1;
		int pageSize = 4;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (Exception e) {}
		
		Page<Customer> page = null;
			page = reportService.getPay(pageNo, pageSize, conditions);
		
		String params = CrmUtils.encodeParameterStringWithPrefix(conditions, "search_");
		
		map.put("page", page);
		map.put("params", params);
		
		return "report/pay";
	}
}
