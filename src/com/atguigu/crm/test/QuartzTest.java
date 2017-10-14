package com.atguigu.crm.test;

import java.util.Date;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzTest {

	public static void main(String[] args) throws SchedulerException {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("quartz.xml");
		
		
		/*
		JobDetailImpl jobDetail = new JobDetailImpl();
		
		jobDetail.setName("jobDetail");
		jobDetail.setGroup("group1");
		jobDetail.setJobClass(MyJob.class);
		
		SimpleTriggerImpl triggerImpl = new SimpleTriggerImpl();
		
		triggerImpl.setStartTime(new Date());
		triggerImpl.setName("trigger1");
		triggerImpl.setGroup("group2");
		triggerImpl.setRepeatCount(5);
		triggerImpl.setRepeatInterval(1000*2);
		
		StdSchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		scheduler.scheduleJob(jobDetail, triggerImpl);
		
		scheduler.start();
		*/
	}
}
