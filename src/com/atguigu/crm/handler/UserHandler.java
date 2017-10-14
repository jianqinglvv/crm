package com.atguigu.crm.handler;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.RoleService;
import com.atguigu.crm.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserHandler {

	@Autowired
	private UserService  userService;
	@Autowired
	private RoleService roleService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value="/shiro-login",method=RequestMethod.POST)
	public String shiroLogin(String username, String password, Map<String,Object> map,HttpSession session,
										Locale locale,RedirectAttributes attributes){
		 Subject currentUser = SecurityUtils.getSubject();
		 
		 if (!currentUser.isAuthenticated()) {
		 UsernamePasswordToken user = new UsernamePasswordToken(username , password);
		 user.setRememberMe(true);
		 Exception ex = null;
         try {
        	 System.out.println("before");
             currentUser.login(user);
             System.out.println("after");
         } catch (UnknownAccountException uae) {
        	 attributes.addFlashAttribute("message", "用户名不存在");
     		ex = uae;
         } catch (IncorrectCredentialsException ice) {
        	 attributes.addFlashAttribute("message", "用户名或密码错误");
     		ex = ice;
         } catch (LockedAccountException lae) {
        	 attributes.addFlashAttribute("message", "账户被锁定");
     		ex = lae;
         }
         catch (AuthenticationException ae) {
        	 attributes.addFlashAttribute("message", "登陆异常");
     		ex = ae;
         }
         
         if(ex != null){
     		attributes.addFlashAttribute("username", username);
     		return "redirect:/shiro-login";
         }
         
		 }
		 Object principal = currentUser.getPrincipals().getPrimaryPrincipal();
         session.setAttribute("user", principal);
         
//		return "redirect:/index";
		return "redirect:/success";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username, String password, Map<String,Object> map,HttpSession session,
										Locale locale,RedirectAttributes attributes){
		
		User user = userService.login(username, password);
		
		if(user != null){
			session.setAttribute("user", user);
			return "redirect:/success";
		}
		
		//通过国际化资源文件获取message
		
		String message = messageSource.getMessage("com.atguigu.crm.login.message", null, locale);
		
		//通过RedirectAttributes设置 redirect 页面可获取的属性
		attributes.addFlashAttribute("message", message);
		attributes.addFlashAttribute("username", username);
			
		return "redirect:/index";
	}
	
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String input(Map<String,Object> map){
		User user = new User();
		user.setEnabled(1);
		map.put("nuser",user );
		map.put("roles", roleService.getRoles());
		return "/user/input";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String save(User user){
		
		userService.save(user);
		
		return "redirect:/user/list";
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id")int id){
		userService.delete(id);
		return "redirect:/user/list";
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id")int id,Map<String,Object> map){
		User user = userService.get(id);
		
		map.put("nuser", user);
		map.put("roles", roleService.getRoles());
		
		return "user/input";
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(User user){
//		Long roleId= user.getRole().getId();
//		Role role = new Role();
//		role.setId(roleId);
//		user.setRole(role);
		
		
		
		
		userService.update(user);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/list"
//			,method=RequestMethod.POST
			)
	public String list(@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNoStr,
					   @RequestParam(value="pageSize",required=false,defaultValue="4")String pageSizeStr,
					   Map<String,Object> map,HttpServletRequest request){
		int pageNo = 1;
		int pageSize = 4;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page<User> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		//search_LIKE_name	search_EQ_enabled
		Map<String, Object> conditions = WebUtils.getParametersStartingWith(request, "search_");
		
		
		userService.getPage(page, conditions );
		
		
		map.put("page", page);
		String params = addPrefix(conditions,"search_");
		map.put("params",params);
		
		System.out.println(params);
		
		return "/user/list";
	}
	private String addPrefix(Map<String, Object> conditions, String prefix) {
		if(conditions == null || conditions.size()==0){
			return null;
		}
		if(prefix==""){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		Set<Entry<String,Object>> set = conditions.entrySet();
		Iterator<Entry<String, Object>> it = set.iterator();
		while(it.hasNext()){
			Entry<String, Object> entry= it.next();
			sb.append(prefix).append(entry.getKey()+"=").append(entry.getValue());
			if(it.hasNext()){
				sb.append("&");
			}
		}
		
		return sb.toString();
	}
}
