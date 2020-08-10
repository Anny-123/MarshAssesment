package com.marsh.Assessment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marsh.Assessment.model.EmployeeAddress;

@Repository
public interface EmployeeAddressRepository extends CrudRepository<EmployeeAddress, Integer> {

	//Extracting employee address with employee id (not by its primary key) with customized query
	
	@Query("from EmployeeAddress where employee_id=:employeeId")
	Optional<EmployeeAddress> findByEmployeeId(@Param("employeeId") int employeeId);

}
