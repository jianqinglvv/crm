package com.atguigu.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.CustomerActivity;

public interface ActivityMapper {

	@SelectKey(before=true,keyProperty="id",resultType=Long.class,statement="select crm_seq.nextval from dual")
	@Insert("insert into customer_activities (id,activity_date,description,place,title,customer_id) "
			+ "values(#{id},#{date,jdbcType=DATE},#{description,jdbcType=VARCHAR},#{place,jdbcType=VARCHAR},#{title,jdbcType=VARCHAR},#{customer.id,jdbcType=NUMERIC}) ")
	void save(CustomerActivity activity);
	
	@Delete("delete from customer_activities where id = #{id}")
	void delete(long id);
	
	@Update("update customer_activities set activity_date=#{date},description=#{description},place=#{place},title=#{title} "
			+ "where id = #{id}")
	void update(CustomerActivity activity);
	
	@Select("select id,activity_date \"date\",description,place,title,customer_id \"customer.id\" "
			+ "from customer_activities "
			+ "where id = #{id} ")
	CustomerActivity get(long id);
	
	@Select("select * from "
			+ "(select rownum rn,id,activity_date \"date\",description,place,title,customer_id \"customer.id\" "
			+ "from customer_activities "
			+ "where customer_id = #{customerId}) t "
			+ "where t.rn>=#{fromIndex} and t.rn<#{endIndex} ")
	List<CustomerActivity> getByCustomerId(Map<String,Object> map);
	
	@Select("select count(id) from customer_activities where customer_id = #{customerId}")
	int getTotalElementsByCustomerId(long customerId);
}
