package com.marsh.Assessment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marsh.Assessment.model.Employee;
import com.marsh.Assessment.service.EmployeeService;

@RestController
@RequestMapping("/info")
public class EmployeeController {

	
	EmployeeService service;

	@Autowired
	public EmployeeController(@Qualifier("employeeServiceImpl")EmployeeService service) {
		// TODO Auto-generated constructor stub
		this.service = service;
	}
	
	
	@GetMapping("/employees")
	public List<Employee> retrieveAllEmployees() {
		return (List<Employee>) service.retrieveAllEmployees();
	}

	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> retrieveEmployee(@PathVariable int employeeId) {

		Employee employee= service.retrieveEmployee(employeeId);


		ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, HttpStatus.OK);

		return response;
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {

		Employee emp = service.createEmployee(employee);

		ResponseEntity<Employee> response = new ResponseEntity<Employee>(emp, HttpStatus.CREATED);

		return response;
	}

	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int employeeId) {
		
		Employee emp = service.deleteEmployee(employeeId);		
		
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(emp, HttpStatus.OK);
		
		return response;
	}


	@PutMapping("/employees")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee){
		
		Employee emp = service.updateEmployee(employee);
		
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(emp, HttpStatus.OK);

		return response;
	}
}
