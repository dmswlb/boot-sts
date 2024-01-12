//package com.shinhan.sbproject;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.shinhan.sbproject.repository.DeptRepository;
//import com.shinhan.sbproject.vo.DepartmentVO;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@SpringBootTest
//public class DeptTest {
//	
//	@Autowired
//	DeptRepository drepo;
//	
//	// 특정 managerId가 관리하는 부서들의 부서이름뒤에 "OK"라는 문자 추가(수정)
//	//@Test
//	void f9() {
//		drepo.findByManagerId(7).forEach(dept->{
//			String dname = dept.getDepartmentName()+"OK";
//			dept.setDepartmentName(dname);
//			drepo.save(dept);
//		});
//		
//	}
//	
//	
//	//입력 10건
//	//@Test
//	void f1() {
//		for(int i=100; i<=200; i+=10) {
//			DepartmentVO dept = DepartmentVO.builder()
//											.departmentName("개발부" + i)
//											.locationId(i)
//											.managerId(i/20)
//											.build();
//			drepo.save(dept);
//		}
//	}
//	
//	//1건 조회
//	//@Test
//	void f2() {
//		DepartmentVO dept = drepo.findById(7).orElse(null);
//		log.info(dept.toString());
//		
//		DepartmentVO dept2 = drepo.findById(8).get();
//		log.info(dept2.toString());
//		
//		drepo.findById(9).ifPresent(b->{
//			log.info(b.toString());
//		});
//	}
//	
//	//모두 조회
//	@Test
//	void f3() {
//		drepo.findAll().forEach(dept->{
//			log.info(dept.toString());
//		});
//	}
//	
//	//수정
//	//@Test
//	void f4() {
//		drepo.findById(9).ifPresent(dept3->{
//			log.info("가져온 data: "+dept3.toString());
//			dept3.setDepartmentName("수정함");
//			drepo.save(dept3);
//		});
//	}
//	
//	//삭제
//	@Test
//	void f5() {
//		drepo.deleteById(11);
//	}
//}
