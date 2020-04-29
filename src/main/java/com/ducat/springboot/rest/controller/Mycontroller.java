package com.ducat.springboot.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ducat.springboot.rest.model.Employee;
import com.ducat.springboot.rest.service.Myservice;

@RestController
public class Mycontroller {

	@Autowired
	Myservice service;
	
//    @Autowired
//    EmployeeService service;

//	@RequestMapping(value= "/employee/all", method= RequestMethod.GET)
//	public List<Employee> getEmployees() {
//		System.out.println(this.getClass().getSimpleName() + " - Get all employees service is invoked.");
//		return service.getEmployees();
//	}
	
	@GetMapping("/employee/all")
	public List<Employee> getEmployee(){
		System.out.println("Getting all employees");
		return service.getEmployees();
	}
		
	@RequestMapping(value= "/employee/id/{id}", method= RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Get employee details by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(id);
		if(!emp.isPresent()) {
			throw new Exception("Could not find employee with id- " + id);
		}

		return emp.get();
	}
	
	@GetMapping("/employee/department/{department}")
	public List<Employee> getEmployeeByDepartment(@PathVariable String department){	
		return service.getEmployeeByDepartment(department);
	}
	
	@GetMapping("/employee/department/{department}/{salary}")
	public List<Employee> getEmployeeByDepartmentAndSalary(@PathVariable String department,@PathVariable double salary){
		return service.getEmployeeByDepartmentAndSalary(department,salary);
	}
	
	@GetMapping("/employee/department")
	public  List<Employee> getEmployeeBySalary(@RequestBody Employee employee ){
			System.out.println("employee"+employee);
			System.out.println("employee1"+employee.getSalary());
			return service.getEmployeeBySalary(employee.getSalary());
	}
	
	
	@RequestMapping(value= "/employee/add", method= RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee newemp) {
		System.out.println(this.getClass().getSimpleName() + " - Create new employee method is invoked.");
		return service.addNewEmployee(newemp);
	}

	@RequestMapping(value= "/employee/update/{id}", method= RequestMethod.PUT)
	public Employee updateEmployee(@RequestBody Employee updemp, @PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Update employee details by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(id);
		if (!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		System.out.println("before"+updemp);
		/* IMPORTANT - To prevent the overiding of the existing value of the variables in the database, 
		 * if that variable is not coming in the @RequestBody annotation object. */		
		if(updemp.getName() == null || updemp.getName().isEmpty())
			updemp.setName(emp.get().getName());
		if(updemp.getDepartment() == null || updemp.getDepartment().isEmpty())
			updemp.setDepartment(emp.get().getDepartment());
		if(updemp.getSalary() == 0)
			updemp.setSalary(emp.get().getSalary());

		// Required for the "where" clause in the sql query template.
		updemp.setId(id);
		System.out.println("after"+updemp);
		return service.updateEmployee(updemp);
	}

	@RequestMapping(value= "/employee/delete/{id}", method= RequestMethod.DELETE)
	public void deleteEmployeeById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete employee by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(id);
		if(!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		service.deleteEmployeeById(id);
	}

	@RequestMapping(value= "/employee/deleteall", method= RequestMethod.DELETE)
	public void deleteAll() {
		System.out.println(this.getClass().getSimpleName() + " - Delete all employees is invoked.");
		service.deleteAllEmployees();
	}
	
	@GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy) 
    {
        List<Employee> list = service.getAllEmployees(pageNo, pageSize, sortBy);
 
        return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
}