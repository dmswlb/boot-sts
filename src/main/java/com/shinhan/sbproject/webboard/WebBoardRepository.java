package com.shinhan.sbproject.webboard;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WebBoardRepository 
	extends CrudRepository<WebBoard, Long>,
			PagingAndSortingRepository<WebBoard, Long>,
			QuerydslPredicateExecutor<WebBoard>{

}
