package com.atguigu.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;

public interface SalesChanceMapper {

	//新增销售机会
	@Insert(value="INSERT INTO sales_chances"
			+ "(id,contact,contact_tel,create_date,cust_name,description,rate,source,status,title,created_user_id)"
			+ "VALUES "
			+ "(crm_seq.nextval,#{contact},#{contactTel},#{createDate},#{custName},"
			+ "#{description},#{rate},#{source},#{status},#{title},#{createBy.id}) ")
	void save(SalesChance chance);
	
	//删除销售机会
	@Delete("DELETE FROM sales_chances WHERE id = #{id}")
	void delete(int id);
	
	//按id获取销售机会
	@Select("SELECT s.id,contact,contact_tel,create_date,cust_name,s.description"
			+ ",rate,source,title,u.name as \"createBy.name\",r.name as \"createBy.role.name\", "
			+ "d.name as \"designee.name\""
			+ "from sales_chances s "
			+ "LEFT OUTER JOIN users d "
			+ "on s.designee_id = d.id "
			+ "LEFT OUTER JOIN users u "
			+ "ON s.created_user_id = u.id "
			+ "LEFT OUTER JOIN roles r "
			+ "ON u.role_id = r.id "
			+ "WHERE s.id = #{id}")
	SalesChance get(int id);
	
	//修改销售机会
//	@Update(value="UPDATE sales_chances "
//			+ "SET contact=#{contact},contact_tel=#{contactTel},cust_name=#{custName},"
//			+ "description=#{description},rate=#{rate},source=#{source},title=#{title},status = #{status} "
//			+ "WHERE id = #{id}")
	void update(SalesChance chance);
	
	
	//指派
	@Update("UPDATE sales_chances set designee_id = #{designee.id},designee_date=#{designeeDate},status = #{status} "
			+ "WHERE id = #{id}")
	void dispatch(SalesChance chance);
	
	//获取指派信息
//	@Select("SELECT id,cust_name,title,contact,contact_tel,create_date,status "
//			+ "FROM sales_chances "
//			+ "WHERE designee_id = #{designeeId}")
//	List<SalesChance> getByDesigneeId(Page<SalesChance> page, Map<String, Object> params);
	
	//分页获取销售机会
//	@Select("SELECT * FROM "
//			+ "(SELECT rownum rn,id,contact,contact_tel,create_date,cust_name"
//			+ ",title "
//			+ "FROM sales_chances) t "
//			+ "WHERE t.rn>=#{fromIndex} and t.rn < #{endIndex}")
	List<SalesChance> getContent(Map<String, Object> map);
	
	
//	@Select("SELECT count(id) FROM sales_chances")
	int getTotalElements(Map<String, Object> mybatisParam);

	

	
}
