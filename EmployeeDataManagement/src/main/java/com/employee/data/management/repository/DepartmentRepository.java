package com.employee.data.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.data.management.model.Department;
import com.employee.data.management.model.Employee;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	@Query(value = "SELECT employees FROM department d WHERE d.id = id", nativeQuery = true)
	List<Employee> findEmployeesById(Long id);

}
