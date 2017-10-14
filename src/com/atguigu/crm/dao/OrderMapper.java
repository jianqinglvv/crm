package com.atguigu.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.Order;

public interface OrderMapper {

	/*
	@SelectKey(before=true,keyProperty="id",statement="select crm_seq.nextval from dual",resultType=Long.class)
	@Insert("insert into orders (id,address,order_date,no,status,customer_id) "
			+ "values(#{id},#{address,jdbcType=VARCHAR},#{date,jdbcType=VARCHAR},#{no},#{status,jdbcType=VARCHAR}"
			+ ",#{customer.id,jdbcType=VARCHAR})")
	void save(Order order);
	*/
	@Select(value="select * from "
			+ "(select rownum rn,id,address,order_date \"date\",no,status,customer_id \"customer.id\" from orders "
			+ "where customer_id = #{customerId})"
			+ " t where rn >= #{fromIndex} and rn < #{endIndex}")
	List<Order> getByCustomerId(Map<String,Object> map);
	
	@Select("select count(id) from orders where customer_id = #{customerId}")
	int getTotalElementsByCustomerId(long id);
	/*
	@Update("update contacts set name=#{name},tel=#{tel},sex=#{sex},memo=#{memo},mobile=#{mobile},"
			+ "position=#{position} "
			+ "where id = #{id}")
	void update(Order order);
	
	@Delete("delete from contacts where id = #{id}")
	void delete(long id);
	*/
	@Select("select id,address,order_date \"date\",no,status,customer_id \"customer.id\""
			+ "from orders "
			+ "where id=#{id} ")
	Order get(long id);
}
