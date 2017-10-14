package com.atguigu.crm.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.atguigu.crm.entity.Storage;

public class CrmUtils {
	
	// LIKE_manager.name:杨倩
		public static <T> Specification<T> parseConditionsToSpecification(final Map<String, Object> conditions) {

			if (conditions == null || conditions.size() == 0) {
				return null;
			}

			Specification<T> specification = new Specification<T>() {

				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
					List<Predicate> predicates = new ArrayList<>();

					for (Map.Entry<String, Object> entry : conditions.entrySet()) {
						String key = entry.getKey();// LIKE_manager.name
						Object value = entry.getValue();// 杨倩
						
						
						if(value == null || value == ""){
							continue;
						}

						String[] keys = key.split("_");
						String operator = keys[0];// LIKE
						String fieldName = keys[1];// manager.name

						String[] names = fieldName.split("\\.");

						// 可能有问题
						// Path<String> path = root.get(names[0]);
						// Path<Object> path = root.get(names[0]);
						Path path = root.get(names[0]);

						if (names.length > 1) {
							for (int i = 1; i < names.length; i++) {
								path = path.get(names[i]);
							}
						}

						Predicate predicate = null;
						switch (operator) {
						case "EQ":
							predicate = builder.equal(path, value);
							break;
						case "NOTEQ":
							predicate = builder.notEqual(path, value);
							break;
						case "LIKE":
							predicate = builder.like(path, "%" + value + "%");
							break;
						case "GE":
							predicate = builder.greaterThanOrEqualTo(path, (Comparable) value);
							break;
						case "LE":
							predicate = builder.lessThanOrEqualTo(path, (Comparable) value);
							break;
						case "GT":
							predicate = builder.greaterThan(path, (Comparable) value);
							break;
						case "LT":
							predicate = builder.lessThan(path, (Comparable) value);
							break;
						}

						if (predicate != null) {
							predicates.add(predicate);
						}
					}
					System.out.println(predicates.size());

					return builder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
			};

			return specification;
		}
	
	

	public static Map<String,Object> changeParamsToMybatisParams(Map<String, Object> params) {
		if(params == null || params.size()==0){
			return null;
		}
		Map<String,Object> map = new HashMap<>();
		
		Set<Entry<String,Object>> entrySet = params.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			String[] strings = entry.getKey().split("_");
			String compare = strings[0];//EQ NE LIKE   and so on
			String key = strings[1];//id name age 
			String value = entry.getValue()+"";
			if(value==null || "".equals(value)){
				System.out.println("-----------"+value);
				continue;
			}
			
			if(compare.equals("LIKE")){
				value = "%"+value+"%";
			}
			if(compare.equals("NE")){
				key = key + "NOT";
			}
			map.put(key, value);
		}
		
		return map;
	}
	
	public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix) {
		if ((params == null) || (params.size() == 0)) {
			return "";
		}

		if (prefix == null) {
			prefix = "";
		}

		StringBuilder queryStringBuilder = new StringBuilder();
		Iterator<Entry<String, Object>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			queryStringBuilder.append(prefix).append(entry.getKey()).append('=').append(entry.getValue());
			if (it.hasNext()) {
				queryStringBuilder.append('&');
			}
		}
		return queryStringBuilder.toString();
	}
	
	private static Map<String,String> turnListToMap(List<String> list){
		Map<String,String> map = new LinkedHashMap<>();

		for (String string : list) {
			map.put(string, string);
		}
		
		return map;
	}
}
