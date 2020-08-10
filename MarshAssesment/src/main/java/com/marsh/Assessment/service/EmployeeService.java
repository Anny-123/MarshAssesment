package com.marsh.Assessment.service;

import java.util.List;

import com.marsh.Assessment.model.Employee;

public interface EmployeeService {
	
	public List<Employee> retrieveAllEmployees() ;

	public Employee retrieveEmployee(int employeeId);
	
	public Employee createEmployee(  Employee employee);

	public Employee deleteEmployee( int employeeId); 
		
	public Employee  updateEmployee(Employee employee);
	
}
