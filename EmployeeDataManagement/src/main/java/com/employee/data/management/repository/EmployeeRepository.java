package com.employee.data.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.data.management.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
