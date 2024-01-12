package com.shinhan.sbproject;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.firstzone.repository.CarRepository;
import com.shinhan.sbproject.vo.CarVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SbprojectApplicationTests {

	@Autowired
	CarRepository repo;
	
	//@Test 
	void f4() {
		repo.findAll().forEach(car->{
			log.info(car.toString());
		});
	}
	
	@Test
	void f3() {
		IntStream.rangeClosed(1, 60).forEach(i -> {

			CarVO c1 = CarVO.builder()
					.model("ABC" + i)
					.price(i * 2000)
					.build();
			repo.save(c1);
		});
	}

	// @Test
	void f2() {
		IntStream.rangeClosed(1, 10).forEach(i -> {

			CarVO c1 = CarVO.builder().carNum(i * 100L).model("ABC" + i).price(i * 2000).build();
			log.info(i + "번째" + c1);
		});
	}

	// @Test
	void f1() {
		CarVO c1 = CarVO.builder().carNum(100L).model("ABC").price(2000).build();
		log.info(c1.toString());
	}

	// @Test
	void contextLoads() {
	}

}
