package com.ducat.springboot.rest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ducat.springboot.rest.model.Employee;

@Repository
public interface PaginationRepository extends PagingAndSortingRepository<Employee,Integer>{

	
}
