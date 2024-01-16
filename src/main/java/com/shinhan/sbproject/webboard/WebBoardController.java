package com.shinhan.sbproject.webboard;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.querydsl.core.types.Predicate;
import com.shinhan.sbproject.security.MemberService;
import com.shinhan.sbproject.vo.MemberDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/webboard")
@Tag(name="웹보드", description="여기에서는 WebBoard CRUD 가능")
public class WebBoardController {
	final WebBoardRepository boardRepo;
	final MemberService mService;
	
	@GetMapping("/list.do")
	public void f1(Principal principal, Authentication auth, HttpSession session, Model model, @ModelAttribute("pageVO") PageVO page) {
		//로그인한 멤버의 정보 알아내기
		//1. Principal 이용
		log.info("방법1: " + principal.toString());
		//2. Authentication 이용
		log.info("방법2: " + auth.getPrincipal());
		//3. SecurityContextHolder 이용
		log.info("방법3: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal()); 
		
		String mid = principal.getName();
		UserDetails user = mService.loadUserByUsername(mid);
		System.out.println("로그인한 유저" + user);
		
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		System.out.println("로그인한 유저(DB)" + member);
		model.addAttribute("user", member);		//page에서 사용하기 위함
	
		
		page.setSize(10);
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
