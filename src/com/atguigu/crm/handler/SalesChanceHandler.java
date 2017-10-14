package com.atguigu.crm.handler;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.dao.ContactMapper;
import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;
import com.atguigu.crm.service.SalesPlanService;
import com.atguigu.crm.service.UserService;

@Controller
@RequestMapping("/chance")
public class SalesChanceHandler {

	@Autowired
	private SalesChanceService salesChanceService;
	@Autowired
	private UserService userService;
	@Autowired
	private SalesPlanService salesPlanService;
	
	
	//客户开发成功
	@RequestMapping(value="/finish/{chanceId}",method=RequestMethod.PUT)
	public String finish(@PathVariable("chanceId")int chanceId,RedirectAttributes redirectAttributes){
		salesChanceService.finish(chanceId);
		
		redirectAttributes.addFlashAttribute("message", "开发完成咯");
		
		return "redirect:/plan/chance/list";
	}
	//开发失败
	@RequestMapping(value="/stop/{chanceId}",method=RequestMethod.PUT)
	public String fail(@PathVariable("chanceId")int chanceId){
		salesChanceService.fail(chanceId);
		
		return "redirect:/plan/chance/list";
	}

	@RequestMapping(value="/details")
	public String detail(@RequestParam(value="chanceId")String chanceIdStr,Map<String,Object> map){
		int chanceId = 0;
		try {
			chanceId = Integer.parseInt(chanceIdStr);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		SalesChance chance = salesChanceService.get(chanceId);
		List<SalesPlan> plans = salesPlanService.getPlans(chanceId);
		
		map.put("chance", chance);
		map.put("plans", plans);
		
		return "plan/detail";
	}
	
	//在指派页面，根据id获取SalesChance信息
	@RequestMapping(value = "/dispatch/{id}", method = RequestMethod.GET)
	public String toDispatch(@PathVariable("id")int id,Map<String,Object> map) {
		SalesChance chance = salesChanceService.get(id);
		map.put("chance", chance);
		map.put("users", userService.getUsers());
		return "/chance/dispatch";
	}
	
	@RequestMapping(value="/dispatch/{id}",method=RequestMethod.PUT)
	public String dispatch(SalesChance chance){
		chance.setStatus(2);
		salesChanceService.dispatch(chance);
//		salesChanceService.update(chance);
		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable(value = "id") Integer id, Map<String, Object> map) {
		map.put("chance", salesChanceService.get(id));
		return "/chance/input";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(SalesChance chance) {
		System.out.println(chance.getStatus());
		
		salesChanceService.update(chance);
		
		
		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {
		map.put("chance", new SalesChance());
		return "/chance/input";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(SalesChance chance, HttpSession session) {
		User user = (User) session.getAttribute("user");
		chance.setCreateBy(user);

		salesChanceService.save(chance);

		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		salesChanceService.delete(id);
		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/list"
//			, method = RequestMethod.POST
			)
	public String getPage(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageSize", required = false, defaultValue = "4") String pageSizeStr,
			Map<String, Object> map, HttpServletRequest request) {

		int pageNo = 1;
		int pageSize = 4;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);

		// search_LIKE_custName search_LIKE_title search_LIKE_contact
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		params.put("EQ_status", 1);

		salesChanceService.getPage(page, params);
		map.put("page", page);

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			sb.append("search_").append(entry.getKey()).append("=" + entry.getValue() + "&");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		System.out.println("----sb-----+" + sb.toString());
		map.put("params", sb.toString());

		return "chance/list";
	}

//	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getPage1(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageSize", required = false, defaultValue = "4") String pageSizeStr,
			Map<String, Object> map, HttpServletRequest request) throws UnsupportedEncodingException {

		int pageNo = 1;
		int pageSize = 4;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);

		// search_LIKE_custName search_LIKE_title search_LIKE_contact
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		params.put("EQ_status", 1);
		
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = new String((entry.getValue()+"").getBytes("ISO-8859-1"), "utf-8");

			System.out.println(key + " :" + value);

			params.put(key, value);
		}

		salesChanceService.getPage(page, params);
		System.out.println(page.getContent().size());
		map.put("page", page);

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			sb.append("search_").append(entry.getKey()).append("=" + entry.getValue() + "&");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		map.put("params", sb.toString());

		return "chance/list";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/listNoParams")
	public String getPageNoParams(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageSize", required = false, defaultValue = "4") String pageSizeStr,
			Map<String, Object> map, HttpServletRequest request) {

		int pageNo = 1;
		int pageSize = 4;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);

		salesChanceService.getPage(page);
		map.put("page", page);

		return "chance/list";

	}

	

}
