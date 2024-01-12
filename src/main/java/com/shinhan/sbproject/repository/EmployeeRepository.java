package com.shinhan.sbproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.sbproject.vo2.EmployeeDTO;

public interface EmployeeRepository extends CrudRepository<EmployeeDTO, Long>{

}
