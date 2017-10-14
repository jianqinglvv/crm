package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.dao.UserMapper;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly=true)
	public User login(String username , String password){
		User user = userMapper.getByName(username);
		
		if( user != null 
				&& user.getEnabled() == 1 
				&& password.equals(user.getPassword())){
			return user;
		}
		
		return null;
	}
	
	@Transactional
	public void save(User user){
		
		String salt = UUID.randomUUID().toString().replace("-", "");
		user.setSalt(salt);
		
		userMapper.save(user);
	}
	@Transactional
	public void delete(int id){
		userMapper.delete(id);
	}
	
	@Transactional
	public void update(User user){
		userMapper.update(user);
	}
	
	@Transactional(readOnly=true)
	public User get(int id){
		return userMapper.get(id);
	}
	@Transactional(readOnly=true)
	public List<User> getContent(Map<String,Object> map){
		return userMapper.getContent(map);
	}
	
	@Transactional
	public List<User> getUsers(){
		return userMapper.getUsers();
	}
	
	
	
	@Transactional(readOnly=true)
	public Page<User> getPage(Page<User> page,Map<String,Object> conditions){
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		
		int fromIndex = (pageNo-1)*pageSize+1;
		int endIndex = fromIndex + pageSize;
		
		Map<String,Object> map = new HashMap<>();
		map.put("fromIndex", fromIndex);
		map.put("endIndex", endIndex);
		if(conditions!=null &&conditions.size()>0){
			
			//需要补充查询条件
			map.putAll(parseConditionsToParams(conditions));
		}
		
		page.setTotalElements(userMapper.getTotalElements(map));
		page.setContent(userMapper.getContent(map));
		
		return page;
	}
	//search_LIKE_name	search_EQ_enabled
		private Map<String,Object> parseConditionsToParams(Map<String, Object> conditions) {
			if(conditions == null || conditions.size()==0){
				return null;
			}
			Map<String,Object> map = new HashMap<>();
			
			Set<Entry<String,Object>> entrySet = conditions.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				String[] strings = entry.getKey().split("_");
				String key = strings[1];
				String value = (String) entry.getValue();
				if(strings[0].equals("LIKE")){
					value = "%"+value+"%";
				}
//				if(strings[0].equals("EQ")){
					//写不写都一样
//				}
				map.put(key, value);
			}
			
			return map;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
