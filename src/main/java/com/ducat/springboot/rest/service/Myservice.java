package com.ducat.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import com.ducat.springboot.rest.model.Employee;

public interface Myservice {

	public List<Employee> getEmployees();
	public Optional<Employee> getEmployeeById(int empid);
	public Employee addNewEmployee(Employee emp);
	public Employee updateEmployee(Employee emp);
	public void deleteEmployeeById(int empid);
	public void deleteAllEmployees();
	public List<Employee> getEmployeeByDepartment(String department);
	public List<Employee> getEmployeeByDepartmentAndSalary(String department,double Salary);
	public List<Employee> getEmployeeBySalary(double Salary);
	List<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy);
	

}