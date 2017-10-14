package com.atguigu.crm.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.atguigu.crm.entity.Customer;

@Repository
public class ReportRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Page<Object[]> getConsist(int pageNo,int pageSize,String type){
		
		String hql = "select count(distinct "+ type +") from Customer where "+ type +" != null";
		
		Query q = entityManager.createQuery(hql);
		
		long total = (long) q.getSingleResult();
		
		String jpql = "select  "+ type +",count(id) from Customer "
				+ "group by "+ type +" "
				+ "having "+ type +" != null "
				+ "order by "+ type +""
		;
		Query query = entityManager.createQuery(jpql)
									.setFirstResult((pageNo - 1)*pageSize)
									.setMaxResults(pageSize)
		;
		
		List<Object[]> list = query.getResultList();
		
		PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
		
		return new PageImpl<>(list, pageRequest, total);
	}
	
	
	public Page<Customer> getPay(int pageNo,int pageSize,String name ,Date fromDate,Date endDate){
		
		String hql = "select count(distinct c.id) from Customer c "
				+ "left join  c.orders o "
				+ "left join  o.items  "
				+ "where c.name like ? and o.date between ? and ? ";
		
		Query q = entityManager.createQuery(hql).setParameter(0, "%"+name+"%")
				.setParameter(1, fromDate)
				.setParameter(2, endDate);
		
		long total =  (long) q.getSingleResult();
		
		String jpql = "select  c from Customer c "
				+ "left join fetch c.orders o "
				+ "left join fetch o.items  "
				+ "where c.name like ? and o.date between ? and ? "
		;
		Query query = entityManager.createQuery(jpql)
									.setParameter(0, "%"+name+"%")
									.setParameter(1, fromDate)
									.setParameter(2, endDate)
									.setFirstResult((pageNo - 1)*pageSize)
									.setMaxResults(pageSize)
		;
		
		List list = query.getResultList();
		
		PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);
		
		return new PageImpl<>(list, pageRequest, total);
	}
}
