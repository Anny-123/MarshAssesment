package com.marsh.Assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marsh.Assessment.exception.EmployeeException;
import com.marsh.Assessment.model.Employee;
import com.marsh.Assessment.model.EmployeeAddress;
import com.marsh.Assessment.repository.EmployeeAddressRepository;
import com.marsh.Assessment.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeRepository employeeRepository;

	EmployeeAddressRepository employeeAddressRepository;

	@Autowired // optional
	public EmployeeServiceImpl(EmployeeRepository employeeRepository,
			EmployeeAddressRepository employeeAddressRepository) {
		this.employeeRepository = employeeRepository;
		this.employeeAddressRepository = employeeAddressRepository;
	}

	@Override
	public List<Employee> retrieveAllEmployees() {
		// TODO Auto-generated method stub
		return (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public Employee retrieveEmployee(int employeeId) {
		// TODO Auto-generated method stub
		Optional<Employee> employee = employeeRepository.findById(employeeId);

		if (!employee.isPresent())
			throw new EmployeeException("Employee Id-" + employeeId + " does not exist");

		return employee.get();
	}

	@Override
	public Employee createEmployee(Employee employee) {

		employeeAddressRepository.save(employee.getEmployeeAddress());

		return employeeRepository.save(employee);
	}

	@Override
	public Employee deleteEmployee(int employeeId) {

		Optional<Employee> employee = employeeRepository.findById(employeeId);

		Optional<EmployeeAddress> address = employeeAddressRepository.findById(employeeId);
		if (!employee.isPresent())
			throw new EmployeeException("Employee Id :" + employeeId + " does not exist");

		Employee emp = employee.get();
		if (address.isPresent())
			emp.setEmployeeAddress(address.get());

		employeeRepository.deleteById(employeeId);
		employeeAddressRepository.deleteById(employeeId);

		return emp;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub

		Optional<Employee> emp = employeeRepository.findById(employee.getEmployeeId());
		//Extracting first address id for updating it
		Optional<EmployeeAddress> address = employeeAddressRepository.findByEmployeeId(employee.getEmployeeId());

		if (!emp.isPresent())
			throw new EmployeeException("Employee Id :" + employee.getEmployeeId() + " does not exist");

		if (address.isPresent() && employee.getEmployeeAddress() != null) {
			employee.getEmployeeAddress().setId(address.get().getId());

			EmployeeAddress savedAddress = employeeAddressRepository.save(employee.getEmployeeAddress());
			employee.setEmployeeAddress(savedAddress); //getting flushed once used
		}

		Employee savedEmp = employeeRepository.save(employee);

		return savedEmp;
	}

}
