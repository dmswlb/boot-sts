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
import org.springframework.data.domain.Sort.Direction;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.sbproject.repository.FreeBoardReplyRepository;
import com.shinhan.sbproject.repository.FreeBoardRepository;
import com.shinhan.sbproject.vo3.FreeBoard;
import com.shinhan.sbproject.vo3.FreeBoardReply;
import com.shinhan.sbproject.vo3.QFreeBoard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TwoWayTest {
	@Autowired
	FreeBoardRepository boardRepo;

	@Autowired
	FreeBoardReplyRepository replyRepo;
	
	@Test
	void f5() {
		//Querydsl 사용
		String title = "연습";
		Long bno = 10L;
		BooleanBuilder builder = new BooleanBuilder();
		QFreeBoard board = QFreeBoard.freeBoard;
		
		if(title!=null) builder.and(board.title.like("%"+title+"%")); //and title like %?1%
		builder.and(board.bno.lt(bno));		//and bno < 10
//		boardRepo.findAll(builder).forEach(b->log.info(b.toString()));		
//		boardRepo.findAll(builder, Sort.by("bno").descending()).forEach(b->log.info(b.toString()));
		
		Pageable page = PageRequest.of(0,  5, Direction.DESC, "bno");
		Page<FreeBoard> result = boardRepo.findAll(builder, page);
		List<FreeBoard> blist = result.getContent();
		log.info("건수: "+result.getTotalElements());
		log.info("페이지수: "+result.getTotalPages());
		blist.forEach(b->log.info(b.toString()));
	}
	
	//@Test
	void f4() {
		String title = "연습2";
		boardRepo.selectByTitle(title).forEach(b->log.info(Arrays.toString(b)));
		log.info("-------------------------------------------------");
		boardRepo.selectByTitle2(title).forEach(b->log.info(Arrays.toString(b)));
		log.info("-------------------------------------------------");
		boardRepo.selectByTitle3(title).forEach(b->log.info(Arrays.toString(b)));
		log.info("-------------------------------------------------");
	}
	
	/*여기 위쪽부터 20240111*/
	
	
	//@Test
	void findByWriter() {
		Pageable paging = PageRequest.of(0, 3);
		boardRepo.findByWriter("user1", paging).forEach(data->{
			log.info("boardnum: "+data.getBno());
			log.info("boardtitle: "+data.getTitle());
			log.info("boardwriter: "+data.getWriter());
			log.info("boardcontent: "+data.getContent());
			log.info("replies개수: " + data.getReplies().size());
			log.info("------------");
		});
	}
	
	//@Test
	void findByBnoBetween() {
		Pageable paging = PageRequest.of(1, 3);
		boardRepo.findByBnoBetween(5L, 10L, paging).forEach(data->{
			log.info("boardnum: "+data.getBno());
			log.info("boardtitle: "+data.getTitle());
			log.info("boardwriter: "+data.getWriter());
			log.info("boardcontent: "+data.getContent());
			log.info("replies개수: " + data.getReplies().size());
			log.info("------------");
		});
	}
	
	//@Test
	void findByBnoGreaterThan() {
		Sort sort = Sort.by(Direction.ASC, "bno");
		Pageable paging = PageRequest.of(1, 3, sort);
		boardRepo.findByBnoGreaterThan(10L, paging).forEach(data->{
			log.info("boardnum: "+data.getBno());
			log.info("boardtitle: "+data.getTitle());
			log.info("boardwriter: "+data.getWriter());
			log.info("boardcontent: "+data.getContent());
			log.info("replies개수: " + data.getReplies().size());
			log.info("------------");
		});
	}
	
	//특정 Board의 댓글 가져오기
	//@Test
	void replySelectByBoard() {
		FreeBoard board = FreeBoard.builder().bno(20L).title("AA").build();
		List<FreeBoardReply> replyList = replyRepo.findByBoard2(board);
		
		replyList.forEach(reply->{
			log.info("rno: " + reply.getRno());
			log.info("reply: " + reply.getReply());
			log.info("replyer: " + reply.getReplyer());
			log.info("regdate: " + reply.getRegdate());
			log.info("updatedate: " + reply.getUpdatedate());
			log.info("--------------------------");
		});
	}
	
	//댓글 가져오기
	//@Test
	void replySelect() {
		replyRepo.findAll(Sort.by(Direction.DESC, "rno")).forEach(reply->{
			log.info("내용: "+reply.getReply());
			log.info("작성자: "+reply.getReplyer());
			log.info("board내용: "+reply.getBoard2().getContent());
			log.info("==========================================");
		});
	}

	//모든 Board의 댓글 개수 출력
	//@Transactional		//지연로딩일때 연관관계 table fetch하려면 반드시 추가
	//@Test
	void getReplyCount() {
		boardRepo.findAll().forEach(board->{
			log.info(board.getBno() + "--->" + board.getReplies().size());
		});
	}
	
	// 특정 Board의 댓글입력: 5L, 10L, 15L
	//@Test
	void relyInsert2() {
		List<Long> blist = Arrays.asList(5L, 10L, 15L);
		boardRepo.findAllById(blist).forEach(board -> {
			IntStream.range(1, 6).forEach(i -> {
				FreeBoardReply reply = FreeBoardReply.builder()
												.reply("퍼스트존"+board.getBno())
												.replyer("작성자" + i)
												.board2(board)
												.build();
				replyRepo.save(reply);
			});
		});

	}

	// 특정 Board의 댓글 입력
	// @Test
	void relyInsert() {
		FreeBoard board = boardRepo.findById(20L).orElse(null);
		IntStream.range(1, 6).forEach(i -> {
			FreeBoardReply reply = FreeBoardReply.builder().reply("나도나도").replyer("작성자" + i).board2(board).build();
			replyRepo.save(reply);
		});
	}

	//@Test
	void boardSelect() {
		boardRepo.findAll(Sort.by(Direction.DESC, "bno")).forEach(b -> log.info(b.toString()));
	}

	// @Test
	void boardInsert() {
		// 20건의 board작성
		IntStream.range(1, 21).forEach(i -> {
			FreeBoard board = FreeBoard.builder().title("양방향연습").writer("user" + i % 5).content("너무어려워").build();
			boardRepo.save(board);
		});
	}
}
