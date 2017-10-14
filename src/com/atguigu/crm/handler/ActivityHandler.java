package com.atguigu.crm.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ActivityService;
import com.atguigu.crm.service.CustService;

@Controller
@RequestMapping("/activity")
public class ActivityHandler {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private CustService custService;
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String input(@RequestParam("customerId")long customerId,Map<String,Object> map){
		CustomerActivity activity = new CustomerActivity();
		Customer customer = new Customer();
		customer.setId(customerId);
		activity.setCustomer(customer);
		
		map.put("activity", activity);
		
		return "activity/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String save(CustomerActivity activity){
		activityService.save(activity);
		Long customerId = activity.getCustomer().getId();
		return "redirect:list/"+customerId;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id")long id,Map<String,Object> map){
		CustomerActivity activity = activityService.get(id);
		
		map.put("activity", activity);
		
		return "activity/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public String update(CustomerActivity activity){
		activityService.update(activity);
		long customerId = activity.getCustomer().getId();
		return "redirect:list/"+customerId;
	}
	
	@RequestMapping("delete")
	public String delete(long id,long customerId){
		activityService.delete(id);
		return "redirect:list/"+customerId;
	}
	
	
	@RequestMapping(value="/list/{customerId}",method=RequestMethod.GET)
	public String list(@PathVariable("customerId")long id,
							@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNoStr,
							@RequestParam(value="pageSize",required=false,defaultValue="4")String pageSizeStr,
							Map<String,Object> map){
		int pageNo = 1;
		int pageSize = 4;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (Exception e) {	}
		
		Page<CustomerActivity> page = new Page<>();
		
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		activityService.getPage(page , id);
		
		Customer customer = custService.get(id);
		map.put("customer", customer);
		map.put("page", page);
		
		return "activity/list";
	}
}
