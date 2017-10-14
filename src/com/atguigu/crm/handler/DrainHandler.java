package com.atguigu.crm.handler;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.DrainService;
import com.atguigu.crm.utils.CrmUtils;

@Controller
@RequestMapping("/drain")
public class DrainHandler {
	
	@Autowired
	private DrainService drainService;
	
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
		
		Page<CustomerDrain> page = new Page<>();
		
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		Map<String, Object> conditions = WebUtils.getParametersStartingWith(request,"search_");
		
		String params = CrmUtils.encodeParameterStringWithPrefix(conditions, "search_");
		
		System.out.println(params);
		
		page = drainService.getPage(page, conditions);

		map.put("page", page);
		map.put("params",params);
		
		
		return "drain/list";
	}
	
	@RequestMapping(value="/delay/{id}",method=RequestMethod.GET)
	public String toDelay(@PathVariable("id")long id,Map<String,Object> map){
		CustomerDrain drain = drainService.get(id);
		
		String delay = drain.getDelay();
		
		if(delay!=null){
			String[] delays = delay.split("`");
		
			map.put("delays", delays);
		}
		map.put("drain", drain);
		
		return "drain/delay";
	}
	
	@RequestMapping(value="/delay",method=RequestMethod.POST)
	public String delay(long id,String delay){
		CustomerDrain drain = drainService.get(id);
		String delays = drain.getDelay();
		if(delays==null || "".equals(delays)){
			drain.setDelay(delay);
		}else{
			delays += "`"+delay;
			drain.setDelay(delays);
		}
		
		drainService.update(drain);
		
		return "redirect:delay/"+id;
		//return "redirect:/drain/list";
	}
	
	@RequestMapping(value="/confirm/{id}",method=RequestMethod.GET)
	public String toConfirm(@PathVariable("id")long id,Map<String,Object> map){
		CustomerDrain drain = drainService.get(id);
		
		String delay = drain.getDelay();

		if(delay!=null){
			String[] delays = delay.split("`");
		
			map.put("delays", delays);
		}
		
		map.put("drain", drain);
		
		return "drain/confirm";
	}
	
	@RequestMapping(value="/confirm",method=RequestMethod.POST)
	public String confirm(long id,String reason){
		CustomerDrain drain = drainService.get(id);
		
		drain.setReason(reason);
		
		drainService.confirm(drain);
		
		return "redirect:list";
		//return "redirect:/drain/list";
	}
	
	
}
