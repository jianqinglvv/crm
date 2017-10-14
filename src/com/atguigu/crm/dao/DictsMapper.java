package com.atguigu.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface DictsMapper {

	@Select(value="select item from dicts where type = '地区' order by id")
	List<String> getResions();
	
	@Select("select item from dicts where type = '客户等级' order by id")
	List<String> getLevels();

	@Select("select item from dicts where type = '满意度' order by id")
	List<String> getSatify();

	@Select("select item from dicts where type = '信用度' order by id")
	List<String> getCredit();

	@Select("select item from dicts where type = '服务类型' order by id")
	List<String> getServe();
}
