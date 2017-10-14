package com.atguigu.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.CustomerDrain;

public interface DrainMapper {

	//调用存储过程
	@Select("{call drain_procedure}")
	void callDrainProcedure();
	/*
	@Select("select cd.id,delay,drain_date drainDate,last_order_date lastOrderDate,reason,status,"
			+ "cd.customer_id \"customer.id\", cust.name as \"customer.name\",ct.name as \"customer.manager.name\" "
			+ "from customer_drains cd "
			+ "left outer join customers cust "
			+ "on cd.customer_id = cust.id "
			+ "left outer join contacts ct "
			+ "on ct.id = cust.manager_id ")
	*/
	List<CustomerDrain> getDrains(Map<String,Object> map);
	
//	@Select("select count(id) from customer_drains")
	int getTotalElements(Map<String,Object> map);
	
	@Select("select cd.id,delay,drain_date drainDate,last_order_date lastOrderDate,reason,status,"
			+ "cd.customer_id \"customer.id\", cust.name as \"customer.name\",ct.name as \"customer.manager.name\" "
			+ "from customer_drains cd "
			+ "left outer join customers cust "
			+ "on cd.customer_id = cust.id "
			+ "left outer join contacts ct "
			+ "on ct.id = cust.manager_id "
			+ "where cd.id = #{id}")
	CustomerDrain get(long id);

	@Update("update customer_drains set delay = #{delay} where id = #{id}")
	void update(CustomerDrain drain);
	
	@Update("update customer_drains set drain_date = #{drainDate },reason = #{reason },status=#{status} "
			+ " where id = #{id}")
	void confirm(CustomerDrain drain);
	
}
