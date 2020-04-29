package com.ducat.springboot.rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ducat.springboot.rest.model.Employee;

@Repository
public interface Mydaorepository extends JpaRepository<Employee, Integer> {

	
//	public List<Employee> findByDepartment(String department);
	@Query(value="SELECT * FROM employee   where department= :department",nativeQuery=true)
	public List<Employee> findByDepartment(@Param("department") String department);
	
	@Query(value="SELECT * FROM employee   where department= :department and salary> :salary",nativeQuery=true)
	public List<Employee> findByDepartmentAndSalary(@Param("department") String department,@Param("salary") double salary);
	
	@Query(value="SELECT * FROM employee where salary> :salary",nativeQuery=true)
	public List<Employee> findBySalary(@Param("salary") double salary);
}