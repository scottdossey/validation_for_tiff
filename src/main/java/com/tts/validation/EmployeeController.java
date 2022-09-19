package com.tts.validation;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository repository;
	
	@GetMapping("/employees")
	ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees= repository.findAll();
		
		return new ResponseEntity<>(employees, HttpStatus.OK);
			
	}
	                         
	 
	@GetMapping("/employees/{id}")
	ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		Employee employee= repository.findById(id);
		if (employee == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	                         
	    
	@PostMapping("/employees")
	ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee,
			                                BindingResult result) {
		
		if(repository.findByEmail(employee.email) != null) {
			result.rejectValue("email", "error.email", "Email already exists");
		}
		if(result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Employee savedEmployee = repository.save(employee);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}
	                         
	@PutMapping("/employees/{id}")                         
	ResponseEntity<Void> updateEmployee(@Valid @RequestBody Employee employee,
			                            BindingResult result,
			                            @PathVariable("id") Long id) {
		if(employee.employeeId != id) {
			result.rejectValue("employeeId", "error.employeeId", "id in body doesn't match");			
		}
		if(result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Employee savedEmployee = repository.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	                         
	@DeleteMapping("/employees/{id}")
	ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
		Employee employee= repository.findById(id);
		if (employee == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping("/employees/{id}")
	ResponseEntity<Void> updateEmployee(@RequestBody Map<String, String> body, 
			                            @PathVariable("id") Long id)
	{
		Employee employee = repository.findById(id);
		
		for(String key : body.keySet())
		{
			switch(key) {		
				case "firstName":
					employee.firstName= body.get(key);
					break;
				case "lastName":
					employee.lastName = body.get(key);
					break;
				case "userName":
					employee.userName = body.get(key);
					break;
				default:
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);						
			}			
		}
		//Should do some more validation of employee to see if valid
		respository.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);
		
		
		
		
		
		
		
		
	}
			
	
	                         
	                         
	
	
	
	
}
