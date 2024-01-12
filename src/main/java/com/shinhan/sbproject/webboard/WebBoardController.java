package com.shinhan.sbproject.webboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/webboard")
public class WebBoardController {
	final WebBoardRepository boardRepo;
	
	@GetMapping("/list.do")
	public void f1(Model model) {
		model.addAttribute("blist", boardRepo.findAll());
	}
	
	@GetMapping("/detail.do")
	public void f2(Model model, Long bno) {
		model.addAttribute("board", boardRepo.findById(bno).orElse(null));
	}
	
	@GetMapping("/update.do")
	public String f3(WebBoard board, RedirectAttributes attr) {
		WebBoard updateboard = boardRepo.save(board);
		attr.addFlashAttribute("message", updateboard!=null?"수정 성공":"수정 실패");
		return "redirect:list.do";
	}
	
	@GetMapping("/insert.do")
	public void f4() {
		
	}
	
	@PostMapping("/insert.do")
	public String f5(WebBoard board, RedirectAttributes attr) {
		WebBoard newboard = boardRepo.save(board);
		attr.addFlashAttribute("message", newboard!=null?"입력 성공":"입력 실패");
		return "redirect:list.do";
	}
	
	@GetMapping("/delete.do")
	public String f6(Long bno, RedirectAttributes attr) {
		boardRepo.deleteById(bno);
		attr.addFlashAttribute("message", "삭제 성공");
		return "redirect:list.do";
	}
}
