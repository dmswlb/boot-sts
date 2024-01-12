package com.shinhan.sbproject.repository;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shinhan.sbproject.vo3.FreeBoard;
import com.shinhan.sbproject.vo3.FreeBoardReply;

public interface FreeBoardReplyRepository extends CrudRepository<FreeBoardReply, Long>, PagingAndSortingRepository<FreeBoardReply, Long>, QuerydslPredicateExecutor<FreeBoardReply>{
	//1. 기본제공 메서드를 이용한다
	//2. 규칙에 맞는 함수를 정의한다
	List<FreeBoardReply> findByBoard2 (FreeBoard b);
	//3.JPQL을 사용한다
}
