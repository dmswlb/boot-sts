package com.shinhan.sbproject.webboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
public class WebBoardReplyController {
	
	@Autowired
	WebReplyRepository replyRepo;
	
	@Autowired
	WebBoardRepository boardRepo;
	
	@GetMapping("/list/{bno}")
	public List<WebReply> f1(@PathVariable ("bno") Long bno) {
		WebBoard board = WebBoard.builder().bno(bno).title("aa").build();
		return replyRepo.findByBoard(board);
	}
	@PostMapping(value = "/add/{bno}", consumes = "application/json")
	public ResponseEntity<List<WebReply>> f2(@PathVariable ("bno") Long bno, @RequestBody WebReply reply) {
		WebBoard board = boardRepo.findById(bno).orElse(null);
		reply.setBoard(board);
		replyRepo.save(reply);
		//상태값과 data를 같이 넘기기
		return new ResponseEntity<>(replyRepo.findByBoard(board), HttpStatus.CREATED) ;
	}
	
	@PutMapping("/update/{bno}")
	public ResponseEntity<List<WebReply>> f3(@PathVariable("bno") Long bno, @RequestBody WebReply reply) {
		replyRepo.findById(reply.getRno()).ifPresent(original->{
			original.setReplyText(reply.getReplyText());
			original.setReplyer(reply.getReplyer());
			replyRepo.save(original);
		});
		
		WebBoard board = WebBoard.builder().bno(bno).title("aa").build();
		return new ResponseEntity<>(replyRepo.findByBoard(board), HttpStatus.OK) ; 
	}
	
	@DeleteMapping("/delete/{bno}/{rno}")
	public ResponseEntity<List<WebReply>> f4(@PathVariable("bno") Long bno, @PathVariable("rno") Long rno) {
		System.out.println("rno: " + rno);
		System.out.println("bno: " + bno);
		
		replyRepo.deleteById(rno);
		WebBoard board = WebBoard.builder().bno(bno).title("aa").build();
		return new ResponseEntity<>(replyRepo.findByBoard(board), HttpStatus.OK) ; 
	}
}
