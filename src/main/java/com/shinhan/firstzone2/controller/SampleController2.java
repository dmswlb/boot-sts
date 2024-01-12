package com.shinhan.firstzone2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.firstzone.repository.BoardRepository;

@RestController
public class SampleController2 {
	
	@Autowired
	BoardRepository brepo;
	
	@GetMapping("/shinhan")
	public String method1() {
		return "Hello " + brepo.count() + "건 있음";
	}
}
