package com.atguigu.crm.handler_jpa;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.service.jpa.StorageService;
import com.atguigu.crm.utils.CrmUtils;

@Controller
@RequestMapping("/storage")
public class StorageHandler {

	@Autowired
	private StorageService storageService;
	
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
		
		System.out.println("pageNo============="+pageNo);
		
		Page<Storage> page = storageService.getPage(pageNo, pageSize, conditions);
		
		String params = CrmUtils.encodeParameterStringWithPrefix(conditions, "search_");
		
		map.put("page", page);
		map.put("params", params);
		
		return "storage/list";
	}
}
