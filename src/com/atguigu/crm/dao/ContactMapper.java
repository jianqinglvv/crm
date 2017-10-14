package com.atguigu.crm.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.Contact;

public interface ContactMapper {
	
	@SelectKey(before=true,keyProperty="id",statement="select crm_seq.nextval from dual",resultType=Long.class)
	@Insert("insert into contacts (id,name,tel,customer_id,sex,memo,position,mobile) "
			+ "values(#{id},#{name,jdbcType=VARCHAR},#{tel,jdbcType=VARCHAR},#{customer.id},#{sex,jdbcType=VARCHAR}"
			+ ",#{memo,jdbcType=VARCHAR},#{position,jdbcType=VARCHAR},#{mobile,jdbcType=VARCHAR})")
	void save(Contact contact);
	
	@Select(value="select * from "
			+ "(select rownum rn,id,name,tel,sex,memo,mobile,position from contacts "
			+ "where customer_id = #{id})"
			+ " t where rn >= #{fromIndex} and rn < #{endIndex}")
	List<Contact> getByCustomerId(Map<String,Object> map);
	
	@Select("select count(id) from contacts where customer_id = #{id}")
	int getTotalElementsByCustomerId(long id);
	
	@Update("update contacts set name=#{name},tel=#{tel},sex=#{sex},memo=#{memo},mobile=#{mobile},"
			+ "position=#{position} "
			+ "where id = #{id}")
	void update(Contact contact);
	
	@Delete("delete from contacts where id = #{id}")
	void delete(long id);
	
	@Select("select ct.id,ct.name,ct.tel,sex,memo,mobile,position,customer_id as \"customer.id\","
			+ "cust.name as \"customer.name\",cust.no as \"customer.no\" "
			+ "from contacts ct "
			+ "left outer join customers cust on cust.id = ct.customer_id "
			+ "where ct.id=#{id} ")
	Contact get(long id);
}
