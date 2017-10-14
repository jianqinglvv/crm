package com.atguigu.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.Customer;

public interface CustMapper {
	
	@SelectKey(before=true,keyProperty="id",statement="select crm_seq.nextval from dual",resultType=Long.class)
	@Insert("insert into CUSTOMERS (id,name,no,state) values(#{id},#{name},#{no},#{state})")
	void save(Customer customer);

	@Select(value="select cust.id,address,bank,bank_account bankAccount,bankroll,chief,credit,fax,licence_no licenceNo,local_tax_no localTaxNo,"
			+ "cust.name,national_tax_no nationalTaxNo,no,region,satify,state,cust.tel,turnover,websit,zip,manager_id as \"manager.id\",ct.name as \"manager.name\" ,customer_level \"level\" from customers cust "
			+ "left outer join contacts ct on ct.id = cust.manager_id "
			+ "where cust.id = #{id}")
	Customer get(long id);
	
	@Update("update customers set address = #{address},bank=#{bank},bank_account=#{bankAccount},bankroll=#{bankroll},"
			+ "chief=#{chief},credit=#{credit,jdbcType=VARCHAR},fax=#{fax},licence_no=#{licenceNo},"
			+ "local_tax_no=#{localTaxNo},"
			+ "name=#{name},national_tax_no=#{nationalTaxNo},no=#{no},region=#{region,jdbcType=VARCHAR},"
			+ "satify=#{satify,jdbcType=VARCHAR},state=#{state},tel=#{tel},turnover=#{turnover},websit=#{websit},"
			+ "zip=#{zip},manager_id=#{manager.id,jdbcType=NUMERIC},customer_level=#{level,jdbcType=VARCHAR} "
			+ "where id = #{id}")
	void update(Customer customer);
	
	//LIKE_name  EQ_region  LIKE_manager.name  EQ_level  EQ_state
	List<Customer> getContent(Map<String,Object> map);
	
	int getTotalElements(Map<String,Object> map);
	
	@Update("update customers set state = '删除' where id = #{id}")
	void delete(long id);
}
