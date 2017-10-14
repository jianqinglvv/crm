package com.atguigu.crm.handler;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.Order;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.OrderService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/order")
public class OrderHandler {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/list/{customerId}",method=RequestMethod.GET)
	public String list(@PathVariable("customerId")long id,
			@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNoStr,
			@RequestParam(value="pageSize",required=false,defaultValue="4")String pageSizeStr,
			Map<String ,Object> map){
		
		int pageNo = 1;
		int pageSize = 4;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (Exception e) {	}
		
		Page<Order> page = new Page<>();
		
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		orderService.getPage(page,id);
		
		map.put("page", page);
		
		
		return "order/list";
	}
	
	
	@RequestMapping(value="/details")
	public String detail(@RequestParam(value="id")String idstr,Map<String,Object> map){
		int id = 0;
		try {
			id = Integer.parseInt(idstr);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		Order order = orderService.get(id);
		
		map.put("order", order);
		
		return "order/details";
	}
}
