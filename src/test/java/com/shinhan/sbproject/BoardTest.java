package com.shinhan.sbproject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.firstzone.repository.BoardRepository;
import com.shinhan.sbproject.vo.BoardVO;
import com.shinhan.sbproject.vo.QBoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
//@RequiredArgsConstructor
public class BoardTest {

	@Autowired
	BoardRepository brepo;
	// final BoardRepository brepo;
	
	@Test
	void f20() {
		BooleanBuilder builder = new BooleanBuilder();
		QBoardVO board = QBoardVO.boardVO;
		
		Long bno = 5L;
		String writer = "user3";
		String content = "억난";
		
		if(bno!=null) builder.and(board.bno.gt(bno));
		if(writer!=null) builder.and(board.writer.eq(writer));
		if(content!=null) builder.and(board.content.like(content));
		
		
		/*
		 * builder.and(board.bno.gt(5L)); builder.and(board.writer.eq("user3"));
		 * builder.and(board.content.like("%억난%"));
		 */
		log.info(builder.toString());
		//boardVO.bno > 5 && boardVO.writer = user3 && boardVO.content like %억난%
		//동적 SQL만들기
		List<BoardVO> blist = (List<BoardVO>) brepo.findAll(builder);
		blist.forEach(b->log.info(b.toString()));
	}
	
	//@Test
	void f19() {
		brepo.selectByWriter("user3").forEach(sarr->{
			log.info("title: " + sarr[0]);
			log.info("content: " + sarr[1]);
			log.info("writer: " + sarr[2]);
			log.info("bno: " + sarr[3]);
			log.info("regDate: " + sarr[4]);
			log.info("=============================");
			
		});
	}
	
	//@Test
	void f18() {		
		List<Object[]> blist = brepo.selectByTitleAndWriter6(5L, "java", "user3");
		blist.forEach(b->log.info(Arrays.toString(b)));
	}
	
	//@Test
	void f17() {
		//Pageable paging = PageRequest.of(1, 6);
//		Pageable paging = PageRequest.of(2, 5, Sort.by(Sort.Direction.DESC, "writer", "title"));
		
		//조건에 맞는 data가 22건, 현재페이지가 5건, 전체 페이지는 5페에지, 마지막페이지는 (0~4)는 2건
		Pageable paging = PageRequest.of(4, 5, Sort.by("writer").ascending());
		
//		Page<BoardVO> result = brepo.findAll(paging);
		Page<BoardVO> result = brepo.findByBnoBetween(10L, 100L, paging);
		
		log.info("페이지사이즈getSize: " + result.getSize());
		log.info("getNumber: " + result.getNumber());
		log.info("getNumberOfElements: " + result.getNumberOfElements());
		log.info("내용건수getTotalElements: " + result.getTotalElements());
		log.info("페이지건수getTotalPages: " + result.getTotalPages());
		log.info("내용getContent: " + result.getContent());
		log.info("getPageable: " + result.getPageable());
		log.info("getSort: " + result.getSort());
		
		result.getContent().forEach(b->log.info(b.toString()));
	}
	
	//@Test
	void f16() {
		Pageable paging = PageRequest.of(1, 6);	//몇 page, pagesize(한 페이지 건수)
		//where bno > 5 .... 6부터 나옴(0페이지에 6건: 6~11)
		//							(1페이지에 6건: 12~17)
		brepo.findByBnoGreaterThan(5L, paging).forEach(b->log.info(b.toString()));
	}

	//@Test
	void f15() {
		String writer = "user3";
		int cnt = brepo.countByWriter(writer);
		log.info("user3이 작성한 board 건수: " + cnt);
		
		brepo.findByWriter(writer).forEach(b->log.info(b.toString()));
	}
	
	// @Test
	void f13() {
		List<BoardVO> blist = brepo.findByContentContainingOrTitleContaining("월요일", "월요일");
		blist.forEach(b -> {
			log.info("findByWriterOrderByRegDateDesc 조회: " + b.toString());
		});
	}

	//@Test
	void f12() {
		List<BoardVO> blist = brepo.findByWriterOrderByRegDateDesc("user3");
		blist.forEach(b -> {
			log.info("findByWriterOrderByRegDateDesc 조회: " + b.toString());
		});
	}

	//@Test
	void f11() {
		List<BoardVO> blist = brepo.findByBnoGreaterThanAndBnoLessThanEqual(10L, 20L);
		blist.forEach(b -> {
			log.info("ContentLike 조회: " + b.toString());
		});
	}

	// @Test
	void f10() {
		List<BoardVO> blist = brepo.findByContentContaining("다");
		blist.forEach(b -> {
			log.info("ContentLike 조회: " + b.toString());
		});
	}

	// @Test
	void f9() {
		List<BoardVO> blist = brepo.findByContentLike("%다%");
		blist.forEach(b -> {
			log.info("ContentLike 조회: " + b.toString());
		});
	}

	// @Test
	void f8() {
		List<BoardVO> blist = brepo.findByBnoGreaterThan(50L);
		blist.forEach(b -> {
			log.info("bno 조회: " + b.toString());
		});
	}

	//@Test
	void f7() {
		List<BoardVO> blist = brepo.findByWriter("user3");
		List<BoardVO> blist2 = brepo.findByContent("재미있다");
		blist.forEach(b -> {
			log.info("writer 조회: " + b.toString());
		});
		blist2.forEach(b -> {
			log.info("content 조회: " + b.toString());
		});
	}

	// @Test
	void f6() {
		log.info("Board건수: " + brepo.count());
	}

	// @Test
	void f5() {
		// 객체 지우기
		Long searchId = 19L;
		brepo.findById(searchId).ifPresent(b -> {
			brepo.delete(b);
		});
		// ID로 지우기
		brepo.deleteById(18L);
	}

	// 수정 update
	// @Test
	void f4() {
		Long searchId = 10L;
		brepo.findById(searchId).ifPresent(b -> {
			b.setTitle("월요일.....");
			b.setContent("미국");
			b.setWriter("aa");
			BoardVO update_board = brepo.save(b);

			log.info("원본: " + b);
			log.info("수정: " + update_board);

		});
	}

	//@Test
	void f3() {
		Long searchId = 20L;
		brepo.findById(searchId).ifPresentOrElse(b -> {
			log.info("조회한 정보: " + b.toString());
		}, () -> {
			log.info("존재하지 않음");
		});
	}

	//@Test
	void f2() {
		brepo.findAll().forEach(board -> {
			log.info(board.toString());
		});
	}

	// 입력 insert
	//@Test
	void f1() {
		IntStream.rangeClosed(21, 40).forEach(i -> {
			BoardVO board = BoardVO.builder().title("java" + i).content("기억난다").writer("user" + i % 5).build();
			BoardVO new_board = brepo.save(board);
			log.info("생성된 Board: " + board);
			log.info("입력된 Board: " + new_board);
			log.info(board.equals(new_board) ? "내용같음" : "내용다름");
			/*
			 * brepo.save(BoardVO.builder() .title("java" + i) .content("기억난다")
			 * .writer("user" + i%5) .build());
			 */

		});
	}
}
