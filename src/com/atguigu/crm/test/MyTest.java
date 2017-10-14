package com.atguigu.crm.test;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;

import com.atguigu.crm.dao.DrainMapper;
import com.atguigu.crm.dao.UserMapper;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.repository.ReportRepository;
import com.atguigu.crm.repository.StorageRepository;

public class MyTest {

	ApplicationContext ctx = null;
	
	UserMapper userMapper = null;
	
	ReportRepository rr = null;
	@Before
	public void before(){
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userMapper = ctx.getBean(UserMapper.class);
//		rr = ctx.getBean(ReportRepository.class);
	}
	
	@Test
	public void testResultMap(){
		User user = userMapper.getByName("a");
		System.out.println(user.getPassword());
		System.out.println(user.getRole().getAuthorities().get(0).getDisplayName());
		System.out.println(user.getRole().getAuthorities().get(0).getParentAuthority());
		
	}
	
	@Test
	public void testRR() throws ParseException{
		Page<Object[]> consist = rr.getConsist(1, 5, "level");
		
		for (Object[] objects : consist.getContent()) {
			System.out.println(objects[0]+"------------"+objects[1]);
		}
		
		System.out.println(consist.getTotalElements());
		/*
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate  = df.parse("1999-01-01");
		Date endDate = df.parse("2999-01-01");;
		String name = "";
		List<Customer> list = rr.getPay(1, 5, name, fromDate, endDate).getContent();
		
		System.out.println("------------"+list.size());
		
		for (Customer customer : list) {
			System.out.println(customer.getName()+"   "
					+customer.getOrderMoney()
					);
		}
		*/
	}
	
	
	@Test
	public void testJPA(){
		StorageRepository sp = ctx.getBean(StorageRepository.class);
//		StoreRepository sp =  ctx.getBean(StoreRepository.class);
		
		System.out.println(sp);
		
		sp.findAll();
	}
	
	
	@Test
	public void testDrainMapper(){
		DrainMapper drainMapper = ctx.getBean(DrainMapper.class);
		
		Map<String, Object> map = new HashMap<>();
		List<CustomerDrain> drains = drainMapper.getDrains(map);
		
		System.out.println(drains.size());
		
	}
	
	
	
	@Test
	public void testUserMapper(){
		
		User user = userMapper.getByName("a");
		
		System.out.println(user.getName());
		System.out.println(user.getRole().getName());
	}
	@Test
	public void test(){
		DataSource dataSource = ctx.getBean(DataSource.class);
		
		System.out.println(dataSource);
	}
	
	@Test
	public void encode() throws UnsupportedEncodingException{
		
		String s = "wd=%E8%BF%AA%E4%B8%BD%E7%83%AD%E5%B7%B4&rsv_idx=2&tn=baiduhome_pg&usm=3&ie=utf-8&rsv_cq=%E8%B5%B5%E4%BB%8A%E9%BA%A6&rsv_dl=0_right_recommends_merge_21102&cq=%E8%B5%B5%E4%BB%8A%E9%BA%A6&srcid=28310&rt=%E4%B8%AD%E5%9B%BD%E8%89%BA%E4%BA%BA&recid=21102&euri=3850a08a43f54c3ebaefe6d12fff1124";	
		String[] split = s.split("&");
//		%E4%B8%AD%E5%9B%BD%E8%89%BA%E4%BA%BA
//		中国艺人
		System.out.println(URLEncoder.encode("中国艺人", "utf-8"));
		System.out.println(URLDecoder.decode("%E4%B8%AD%E5%9B%BD%E8%89%BA%E4%BA%BA", "utf-8"));
		System.out.println(URLDecoder.decode("中国艺人", "utf-8"));
		System.out.println(URLEncoder.encode("%E4%B8%AD%E5%9B%BD%E8%89%BA%E4%BA%BA", "utf-8"));
	
		System.out.println(URLDecoder.decode("%25E4%25B8%25AD%25E5%259B%25BD%25E8%2589%25BA%25E4%25BA%25BA", "utf-8"));
	}
	
	@Test
	public void testTrueFalse(){
		
	}
	
}
