package com.employee.data.management.interceptors;

public class DepartmentNotFound extends RuntimeException{
	
	public DepartmentNotFound(String message) {
		super(message);
	}

}
