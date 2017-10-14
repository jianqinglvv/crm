package com.atguigu.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.SalesPlan;

public interface SalesPlanMapper {

	//新增
//		@Insert(value="insert into sales_plan (id,plan_date,plan_result,todo,chance_id) "
//				+ "values (crm_seq.nextval,#{date},#{result},#{todo},#{chance.id})")
		void save(SalesPlan plan);
		
		//删除
		@Delete("delete from sales_plan where id = #{id}")
		void delete(int id);
		
		//按id获取
//		@Select(" ")
//		SalesPlan get(int id);
		
		//修改
//		@Update(value="update sales_plan set todo = #{todo} where id = #{id}")
		void update(SalesPlan plan);
		
		//分页获取
		@Select("select id,plan_date as \"date\",plan_result as result, todo from sales_plan where chance_id = #{chanceId}")
		List<SalesPlan> getContent(Map<String, Object> map);
		
		
//		@Select(" ")
//		int getTotalElements(Map<String, Object> mybatisParam);
}
