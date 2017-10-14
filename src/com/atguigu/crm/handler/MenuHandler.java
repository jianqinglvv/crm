package com.atguigu.crm.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.User;

import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.MenuRepository;

@Controller
public class MenuHandler {

	@RequestMapping("/menuList")
	public String menuList(HttpSession session,Map<String,Object> map){
		ServletContext application = session.getServletContext();
		String contextPath = application.getContextPath();
		
		MenuRepository repository = new MenuRepository();
		
		MenuRepository defaultRepository = (MenuRepository)
		        application.getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
		
		repository.setDisplayers(defaultRepository.getDisplayers());

		    MenuComponent mc = new MenuComponent();
		    mc.setName("crm");
		    mc.setTitle("客户关系管理系统");
		    
		    User user = (User) session.getAttribute("user");
		    Role role = user.getRole();
		    List<Authority> authorities = role.getAuthorities();
		    
//		    System.out.println(authorities.size()+"个Menu即将生生");
		    
		    Map<String,MenuComponent> parentMenus = new HashMap<>();
		    for (Authority authority : authorities) {
		    	MenuComponent menu = new MenuComponent();
		    	menu.setName(authority.getName());
		    	menu.setTitle(authority.getDisplayName());
		    	menu.setLocation(contextPath+authority.getUrl());
		    	
		    	
		    	Authority parentAuthority = authority.getParentAuthority();
		    	MenuComponent parentMenu = parentMenus.get(parentAuthority.getId()+"");
		    	if(parentMenu == null){
		    		parentMenu = new MenuComponent();
		    		parentMenu.setName(parentAuthority.getId()+"");
		    		parentMenu.setTitle(parentAuthority.getDisplayName());
		    		
//		    		System.out.println("Menu"+parentAuthority.getDisplayName());
		    		
		    		parentMenus.put(parentAuthority.getId()+"", parentMenu);
		    		parentMenu.setParent(mc);
		    	}
		    	
//		    	System.out.println("Menu"+authority.getDisplayName());
		    	
		    	menu.setParent(parentMenu);
			}
		    
		    repository.addMenu(mc);
		map.put("repository", repository);
		
		return "home/menu";
	}
}
