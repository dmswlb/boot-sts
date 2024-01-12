package com.shinhan.sbproject.vo5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DeptEmpController {

	@Autowired
	EmpRepository empRepo;
	
	@Autowired
	DeptRepository deptRepo;
	
	@GetMapping("/deptemp")
	public String f1(Model model) {
		model.addAttribute("dlist", deptRepo.findAll());
		model.addAttribute("elist", empRepo.findAll());
		return "dept";
	}
//	@GetMapping("/dept")
//	public void f1(Model model) {
//		model.addAttribute("dlist", deptRepo.findAll());
//	}
//	@GetMapping("/emp")
//	public List<EmpVO> f2() {
//		return (List<EmpVO>) empRepo.findAll();
//	}
}
