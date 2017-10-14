package com.atguigu.crm.baseRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BaseRepository<T> extends JpaRepository<T, Long> ,JpaSpecificationExecutor<T> {
//public interface BaseRepository<T> extends PagingAndSortingRepository<T , Long> ,JpaSpecificationExecutor<T> {
	
}
