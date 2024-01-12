package com.shinhan.firstzone.repository;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.vo.CarVO;

public interface CarRepository extends CrudRepository<CarVO, Long>{
	
}
