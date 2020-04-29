package com.ducat.springboot.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ducat.springboot.rest.dao.Mydaorepository;
import com.ducat.springboot.rest.dao.PaginationRepository;
import com.ducat.springboot.rest.model.Employee;

@Service
public class Myserviceimpl implements Myservice {

	@Autowired
	Mydaorepository dao;
	
	@Autowired
	PaginationRepository repository;

	@Override
	public List<Employee> getEmployees() {
		return dao.findAll();
	}
	@Override
	public Optional<Employee> getEmployeeById(int empid) {
		return dao.findById(empid);
	}
	@Override
	public Employee addNewEmployee(Employee emp) {
		System.out.println("emp"+emp);
		return dao.save(emp);
	}
	@Override
	public Employee updateEmployee(Employee emp) {
		return dao.save(emp);
	}
	@Override
	public void deleteEmployeeById(int empid) {
		dao.deleteById(empid);
	}
	@Override
	public void deleteAllEmployees() {
		dao.deleteAll();
	}
	
	@Override
	public List<Employee> getEmployeeByDepartment(String name){
		return dao.findByDepartment(name);
	}
	
	@Override
	public List<Employee> getEmployeeByDepartmentAndSalary(String department,double salary){
		return dao.findByDepartmentAndSalary(department,salary);
	}
	
	@Override
	public List<Employee> getEmployeeBySalary(double salary){
		return dao.findBySalary(salary);
	}
	
	@Override
	public List<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy){
	      Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
	      Page<Employee> pagedResult = repository.findAll(paging);   
	        if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Employee>();
	        }
	    }
}