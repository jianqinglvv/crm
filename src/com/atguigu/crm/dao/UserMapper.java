package com.atguigu.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.User;

public interface UserMapper {

//	@Select("select * from users where name = #{name}")
	
	/*
	@Select("select u.id ,u.name ,u.enabled ,password ,salt ,r.name as \"role.name\" "
			+ " from users u left outer join roles r"
			+ " on  u.role_id = r.id where u.name =#{name}")
			*/
	User getByName(@Param("name")String username);
	
	@Insert("insert into users (id,name,enabled,password,role_id,salt)"
			+ "values (crm_seq.nextval,#{name},#{enabled},#{password},#{role.id},#{salt})")
	void save(User user);
	
	@Delete("delete from users where id =#{id}")
	void delete(int id);
	
//	@Update("update users set name =#{name},password=#{password},"
//			+ "enabled=#{enabled},role_id =#{role.id} where id=#{id}")
	void update(User user);
	
	@Select("select id,name,enabled,role_id as \"role.id\" from users where id=#{id}")
	User get(int id);
	
//	@Select("select * from (select rownum rn, id,name,enabled from users) t "
//			+ "where t.rn >=#{fromIndex} and t.rn < #{endIndex}")
	List<User> getContent(Map<String,Object> map);
	
//	@Select(value="select count(id) from users ")
	int getTotalElements(Map<String,Object> map);
	
	@Select("select id,name,enabled from users")
	List<User> getUsers();
}
