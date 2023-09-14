package com.employee.data.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.data.management.DTO.EmployeeDTO;
import com.employee.data.management.model.Employee;
import com.employee.data.management.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employeedetails")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }
	
	@PostMapping("/createEmployee")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employee){
		return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.OK);
	}
	
	@GetMapping("/employeebydepartment/{id}")
    public ResponseEntity<?> employeeByDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.employeeByDepartment(id), HttpStatus.OK);
    }

}
