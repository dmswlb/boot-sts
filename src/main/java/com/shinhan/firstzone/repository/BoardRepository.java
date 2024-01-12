package com.shinhan.firstzone.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.shinhan.sbproject.vo.BoardVO;

//1. 기본 CRUD 작업: CrudRepository 상속: findAll, findById, save, count, delete
//CrudRepository을 상속 받은 PagingAndSortingRepository
public interface BoardRepository extends CrudRepository<BoardVO, Long>,PagingAndSortingRepository<BoardVO, Long>, ListQuerydslPredicateExecutor<BoardVO>{
	//2. 규칙에 맞는 메서드 정의
	List<BoardVO>  findByWriter(String writer2);		//where writer =?
	List<BoardVO>  findByContent(String content);		//where content =?
	List<BoardVO>  findByBnoGreaterThan(Long bno);	//where bno>?
	List<BoardVO>  findByContentLike(String content2);	//where content like=?
	List<BoardVO>  findByContentContaining(String content2);	//where content like= '%'||?||'%'
	List<BoardVO>  findByBnoGreaterThanAndBnoLessThanEqual(Long bno, Long bno2);	//where bno > ? and bno <=?
	List<BoardVO> findByBnoLessThan(Long bno); // where bno < ?
	List<BoardVO> findByWriterOrderByRegDateDesc(String writer); // where writer = ? order by RegDateDesc
	List<BoardVO> findByContentContainingOrTitleContaining(String Content2, String title); // where content like '%'||?||'%' or title like '%'||?||'%' 
	
	//특정 writer가 작성한 board의 건수 
	int countByWriter(String writer2);
	
	//Paging, Sort 추가
	List<BoardVO> findByBnoGreaterThan(Long bno, Pageable paging);
	Page<BoardVO> findByBnoBetween(Long bno1, Long bno2, Pageable paging);
	
	//3.JPQL(JPA Query language): 규칙에 맞는 함수 정의가 한계가 있다. 이를 해결하는 방법이다
	//'*' 사용금지
	@Query("select b from BoardVO b where b.title like %?2% and b.writer like ?3 and b.bno> ?1 order by bno desc")
	List<BoardVO> selectByTitleAndWriter2(Long bno, String title, String writer);
	
	//SQL문 (nativeQuery = true), 테이블 이름, *도 가능
	@Query(value ="select * from tbl_boards_review b where b.title like %?2% and b.writer like ?3 and b.bno> ?1 order by bno desc", nativeQuery = true)
	List<BoardVO> selectByTitleAndWriter3(Long bno, String title, String writer);

	@Query("select b from BoardVO b where b.title like %:tt% and b.writer like %:ww% and b.bno> :bb order by bno desc")
	List<BoardVO> selectByTitleAndWriter4(@Param("bb") Long bno, @Param("tt") String title, @Param("ww") String writer);
	
	@Query("select b from #{#entityName} b where b.title like %:tt% and b.writer like %:ww% and b.bno> :bb order by bno desc")
	List<BoardVO> selectByTitleAndWriter5(@Param("bb") Long bno, @Param("tt") String title, @Param("ww") String writer);

	@Query("select b.title, b.writer, b.content from #{#entityName} b where b.title like %:tt% and b.writer like %:ww% and b.bno> :bb order by bno desc")
	List<Object[]> selectByTitleAndWriter6(@Param("bb") Long bno, @Param("tt") String title, @Param("ww") String writer);
	
	@Query("select board.title, board.content, board.writer, board.bno, board.regDate from #{#entityName} board where board.writer = :wr")
	List<String[]> selectByWriter(@Param("wr") String writer);

}
