package com.shinhan.sbproject.controller;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shinhan.sbproject.repository.FreeBoardReplyRepository;
import com.shinhan.sbproject.repository.FreeBoardRepository;
import com.shinhan.sbproject.vo3.FreeBoard;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ThymeleafController {
	
	@Autowired
	FreeBoardRepository brepo;
	
	@GetMapping("/hello3")
	public void f4(Model model, HttpServletRequest request, HttpSession session) {
		model.addAttribute("myname", request.getParameter("name"));
		model.addAttribute("myname2", session.getId());
		model.addAttribute("now", new Date());
		model.addAttribute("price", 123456789);
		model.addAttribute("title", "This is a just sample");
		model.addAttribute("options", Arrays.asList("AA", "BB", "CC"));
	}
	
	@GetMapping("/freeboard/list")
	public void f3(Model model) {
		model.addAttribute("loginUser", "user1");
		model.addAttribute("myFriend", "user2");
		model.addAttribute("blist", brepo.findAll());
	}
	
	@GetMapping("/hello2")
	public String f2(Model model) {
		model.addAttribute("greeting", "안녕");
		FreeBoard board = FreeBoard.builder()
							.bno(99L)
							.title("글제목")
							.writer("작성자")
							.regdate(new Timestamp(System.currentTimeMillis()))
							.build();
		model.addAttribute("board", board);
		return "hello1";
	}
	
	@GetMapping("/hello1")
	public void f1(Model model) {
		
		log.info("hello 요청");
		model.addAttribute("greeting", "감사");
		model.addAttribute("board", brepo.findById(6L).orElse(null));
		//접두사: classPath: templates
		//접미사: .html
		//returnL "templates/hello1.html"
	}
}
