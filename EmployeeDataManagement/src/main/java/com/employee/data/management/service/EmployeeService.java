package com.employee.data.management.service;

import org.springframework.http.ResponseEntity;

import com.employee.data.management.DTO.EmployeeDTO;

public interface EmployeeService {

	ResponseEntity<?> getAll();

	ResponseEntity<?> createEmployee(EmployeeDTO employee);

	ResponseEntity<?> employeeByDepartment(Long id);

}
