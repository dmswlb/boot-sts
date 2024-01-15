package com.shinhan.sbproject.webboard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.querydsl.core.types.Predicate;

import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/webboard")
public class WebBoardController {
	final WebBoardRepository boardRepo;
	
	@GetMapping("/list.do")
	public void f1(Model model, @ModelAttribute("pageVO") PageVO page) {
		Predicate predicate = boardRepo.makePredicate(page.getType(), page.getKeyword());
		System.out.println("[[page]]]" + page);
		Pageable paging = page.makePageable(page.getPage()-1, "bno");	//0:desc sort, bno: sort 기준 칼럼
		boardRepo.findAll(predicate, paging);
		Page<WebBoard> result = boardRepo.findAll(predicate, paging);
		
		PageMarker<WebBoard> pageMaker = new PageMarker<>(result, 5, page.getSize());
		model.addAttribute("blist", pageMaker);
		//paging, predicate, sort 추가
		
//		page.setType("title");
		
//		model.addAttribute("blist", boardRepo.findAll());
	}
	
	@GetMapping("/detail.do")
	public void f2(Model model, Long bno, @ModelAttribute("pageVO") PageVO page) {
		model.addAttribute("board", boardRepo.findById(bno).orElse(null));
	}
	
//	@PostMapping("/update.do")
//	public String f3(WebBoard board, RedirectAttributes attr, PageVO page) {
//		WebBoard updateboard = boardRepo.save(board);
//		attr.addFlashAttribute("message", updateboard!=null?"수정 성공":"수정 실패");
////		attr.addAttribute("message", page.getPage());
////		attr.addAttribute("message", page.getSize());
////		attr.addAttribute("message", page.getKeyword());
////		attr.addAttribute("message", page.getType());
//		return "redirect:list.do";
//	}
	@GetMapping("/update.do")
	public String f3(WebBoard board, RedirectAttributes attr, PageVO page) {
		WebBoard updateboard = boardRepo.save(board);
		attr.addFlashAttribute("message", updateboard!=null?"수정 성공":"수정 실패");
//		attr.addAttribute("message", page.getPage());
//		attr.addAttribute("message", page.getSize());
//		attr.addAttribute("message", page.getKeyword());
//		attr.addAttribute("message", page.getType());
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
