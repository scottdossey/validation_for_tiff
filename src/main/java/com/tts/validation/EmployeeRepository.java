package com.tts.validation;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	@Override
	List<Employee> findAll();	
	
	List<Employee> findByCreationDateBetween(Date start, Date end);
	
	
}
