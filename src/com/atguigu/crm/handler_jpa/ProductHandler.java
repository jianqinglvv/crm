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

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.service.jpa.ProductService;
import com.atguigu.crm.utils.CrmUtils;

@Controller
@RequestMapping("/product")
public class ProductHandler {

	@Autowired
	private ProductService productService;
	
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
		
		Page<Product> page = productService.getPage(pageNo, pageSize, conditions);
		
		String params = CrmUtils.encodeParameterStringWithPrefix(conditions, "search_");
		
		map.put("page", page);
		map.put("params", params);
		
		return "product/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String input(Map<String,Object> map){
		
		Product product = new Product();
		map.put("product", product);
		
		return "product/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String save(Product product){
		productService.save(product);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id")long id,Map<String,Object> map){
		
		System.out.println(id);
		
		Product product = productService.get(id);
		
		System.out.println(product);
		
		map.put("product", product);
		
		return "product/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public String update(Product product){
		productService.save(product);
		
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")long id){
		productService.delete(id);
		
		return "redirect:list";
	}
	
}
