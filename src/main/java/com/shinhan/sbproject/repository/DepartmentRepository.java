package com.shinhan.sbproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.vo.DepartmentVO;
import com.shinhan.sbproject.vo2.DepartmentDTO;

public interface DepartmentRepository extends CrudRepository<DepartmentDTO, Long>{

}
