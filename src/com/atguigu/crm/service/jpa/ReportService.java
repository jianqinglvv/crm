package com.atguigu.crm.service.jpa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public Page<Object[]> getConsist(int pageNo, int pageSize,Map<String,Object> params) {
		String type = (String) params.get("type");
		
		if(type == null || type == ""){
			type = "level";
		}
		type = type.trim();
		
		return reportRepository.getConsist(pageNo, pageSize, type);
	}
	
	
	public Page<Customer> getPay(int pageNo, int pageSize,Map<String,Object> params) {
		String name = (String) params.get("name");
		
		String date1 = params.get("date1")+"";
		String date2 = params.get("date2")+"";
		
		Date fromDate = null;
		Date endDate = null;
		
		try {
			fromDate = df.parse(date1);
		} catch (ParseException e) {
			try {
				fromDate = df.parse("1970-01-01");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
			endDate = df.parse(date2);
		} catch (Exception e) {
			try {
				endDate = df.parse("2099-12-31");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(name == null){
			name = "";
		}
		name = name.trim();
		
		return reportRepository.getPay(pageNo, pageSize, name, fromDate, endDate);
	}
}
