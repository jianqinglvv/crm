package com.atguigu.crm.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ContactService;
import com.atguigu.crm.service.CustService;

@Controller
@RequestMapping("/contact")
public class ContactHandler {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private CustService custService;
	
	@RequestMapping(value="/input",method=RequestMethod.GET)
	public String input(long customerId,Map<String,Object> map){
		Contact contact = new Contact();
		Customer customer = new Customer();
		customer.setId(customerId);
		contact.setCustomer(customer);
		
		map.put("contact", contact);
		
		return "contact/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String save(Contact contact){
		
		contactService.save(contact);
		long customerId = contact.getCustomer().getId();
		return "redirect:/contact/list/"+customerId;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id")long id,Map<String,Object> map){
		
		Contact contact = contactService.get(id);
		
		map.put("contact", contact);
		
		return "contact/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public String update(Contact contact){
		contactService.update(contact);
		long customerId = contact.getCustomer().getId();
		return "redirect:/contact/list/"+customerId;
	}
	@RequestMapping(value="delete")
	public String delete(long id,long customerId){
		contactService.delete(id);
		return "redirect:/contact/list/"+customerId; 
	}
	
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
		
		Page<Contact> page = new Page<>();
		
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		contactService.getPage(page,id);
		
		map.put("page", page);
		
		Customer customer = custService.get(id);
		
		map.put("customer", customer);
		
		return "contact/list";
	}
}
