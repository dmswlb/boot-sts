package com.shinhan.sbproject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.sbproject.repository.DepartmentRepository;
import com.shinhan.sbproject.repository.EmployeeRepository;
import com.shinhan.sbproject.vo2.DepartmentDTO;
import com.shinhan.sbproject.vo2.EmployeeDTO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class OneToManyTest2 {
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	DepartmentRepository deptRepo;
	
	@Test
	void empUpdate() {
		//40번 직원은 100번 부서로 배치
		EmployeeDTO emp40 = empRepo.findById(40L).orElse(null);
		deptRepo.findById(100L).ifPresent(dept->{
			if(emp40!=null) {
				dept.getEmplist().add(emp40);
				deptRepo.save(dept);
			}			
		});
		
		//50번 직원은 신규부서 200번으로 배치
		EmployeeDTO emp50 = empRepo.findById(50L).orElse(null);
		List<EmployeeDTO> elist = new ArrayList<>();
		elist.add(emp50);
		DepartmentDTO dept = DepartmentDTO.builder()
											.deptId(200L)
											.deptName("TF팀")
											.emplist(elist)
											.build();
		deptRepo.save(dept);
	}
	
	//@Test
	void selectEmp() {
		empRepo.findAll().forEach(emp->log.info(emp.toString()));
	}
	
	//@Test
	void insertEmp() {
		EmployeeDTO emp1 = EmployeeDTO.builder().empId(40L).empName("경력사원").build();
		EmployeeDTO emp2 = EmployeeDTO.builder().empId(50L).empName("김부장").build();
		empRepo.save(emp1);
		empRepo.save(emp2);
	}
	
	//@Test
	void selectDept() {
		deptRepo.findAll().forEach(d->{
			log.info(d.toString());
			d.getEmplist().forEach(emp->{
				log.info("직원정보: " + emp);
			});
		});
	}
	
	//@Test
	void insertDeptEmp() {
		List<EmployeeDTO> elist = new ArrayList<>();
		IntStream.rangeClosed(1, 3).forEach(i->{
			EmployeeDTO emp = EmployeeDTO.builder()
											.empId(i+10L)
											.empName("우수사원: " + i)
											.build();
			elist.add(emp);
		});
		DepartmentDTO dept = DepartmentDTO.builder()
											.deptId(100L)
											.deptName("개발부")
											.emplist(elist)
											.build();
		deptRepo.save(dept);
	}
}
