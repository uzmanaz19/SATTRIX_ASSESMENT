package com.employee.data.management.serviceImpl;

import java.util.*;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.data.management.DTO.AddressDTO;
import com.employee.data.management.DTO.DepartmentDTO;
import com.employee.data.management.DTO.EmployeeDTO;
import com.employee.data.management.interceptors.DepartmentNotFound;
import com.employee.data.management.model.*;
import com.employee.data.management.repository.DepartmentRepository;
import com.employee.data.management.repository.EmployeeRepository;
import com.employee.data.management.service.EmployeeService;

import ch.qos.logback.classic.Logger;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public ResponseEntity<List<Employee>> getAll() {
		return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
	}
	
	Logger logger = (Logger) LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public ResponseEntity<?> createEmployee(EmployeeDTO employee) {
		Employee emp = new Employee();
		emp.setName(employee.getName());
		emp.setGender(employee.getGender());
		emp.setContact(employee.getContact());
		List<Address> addresses = new ArrayList<Address>();
		List<AddressDTO> addList = employee.getAddresses();
		for(AddressDTO dto : addList) {
			Address add = new Address();
			add.setStreet1(dto.getStreet1());
			add.setStreet2(dto.getStreet2());
			add.setCity(dto.getCity());
			add.setState(dto.getState());
			addresses.add(add);
		}
		emp.setAddresses(addresses);
		emp.setDepartment(employee.getDepartment());
		List<Department> deptList = new ArrayList<>();
		for(DepartmentDTO dto : employee.getDepartments()) {
			Department dept = new Department();
			dept.setDepartmentName(dto.getDepartmentName());
			deptList.add(dept);
		}
		emp.setDepartments(deptList);
		logger.info("Employee data added successfully");
		return new ResponseEntity<>(employeeRepository.save(emp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> employeeByDepartment(Long id) {
		List<Employee> empList = departmentRepository.findEmployeesById(id);
		if(empList.isEmpty()) {
			logger.info("Department not found");
			throw new DepartmentNotFound("Department not found");
		}
		return new ResponseEntity<>(departmentRepository.findEmployeesById(id), HttpStatus.OK);
	}

}
