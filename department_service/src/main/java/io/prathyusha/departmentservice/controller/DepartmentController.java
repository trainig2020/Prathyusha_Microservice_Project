package io.prathyusha.departmentservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.prathyusha.departmentservice.model.Department;
import io.prathyusha.departmentservice.model.DepartmentList;
import io.prathyusha.departmentservice.model.Employee;
import io.prathyusha.departmentservice.model.EmployeeList;
//import io.prathyusha.departmentservice.model.DepartmentList;
import io.prathyusha.departmentservice.service.DepartmentService;
import io.prathyusha.departmentservice.service.EmployeeService;


@RestController
@RequestMapping("/Department")
public class DepartmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	
    @Autowired 
	private EmployeeService employeeService;
	 
	
   @RequestMapping("/listDept")
		public DepartmentList getAllDepartments(){
		List<Department> list = departmentService.getAllDepartments();
		logger.info("Get All Departments.....");
		DepartmentList listOfDepartments = new DepartmentList();
		listOfDepartments.setDeptList(list);
	    return listOfDepartments;	
	}
	
	@RequestMapping("/{deptId}")
	   public Optional<Department> getDepartment(@PathVariable("deptId") int deptId) {
		logger.info("Get Department by Id.....");
		return departmentService.getDepartment(deptId);
	}
	
	
	@PostMapping("/addDepartment")
	    public Department addDepartment(@RequestBody Department department) {
		logger.info("Add Department.....");
		return departmentService.addDepartment(department);
	}
	
	
	 @PutMapping("/updateDepartment/{deptId}")
	      public void updateDepartment(@RequestBody Department department, @PathVariable int deptId) { 
		 logger.info("Update Department by Id....");
		  department.setDeptId(deptId);
	      departmentService.updateDepartment(department);
	  }
	
	@DeleteMapping("/deleteDepartment/{deptId}")
	      public void deleteDepartment(@PathVariable int deptId) {
		logger.info("Delete Department by Id....");
		   departmentService.deleteDepartment(deptId);
	      employeeService.deleteEmployee(deptId);
	  }
	
	
	  @GetMapping("{eDid}/employees")
	  public EmployeeList getAllEmployees(@PathVariable int eDid){ 
	  EmployeeList lst =employeeService.getAllEmployees(eDid);
	  logger.info("Getting Employees w.r.t DepartmentId.... ");
	  System.out.println(lst.getListOfEmployees().size()); 
	  System.out.println(lst.getListOfEmployees().size()); return lst;
	  
	  }
	   
	  @GetMapping("/employees/{empId}") public Employee getEmployee(@PathVariable int empId) {
		  logger.info("Getting Employees w.r.t EmployeeId....");
		  return employeeService.getEmployee(empId); 
		  
	  }
	  
	  
	  
	  @PostMapping("/employees/{eDid}/addEmployee") 
	  public void addEmployee(@RequestBody Employee employee,@PathVariable int eDid) {
		  logger.info("Add Employee in particular DepartmentId....");
	      employee.seteDid(eDid);
	     employeeService.addEmployee(employee,eDid);
	  }
	  
	  
	  
	  @PutMapping("/employees/{eDid}/updateEmployee/{empId}")
	  public void updateEmployee(@RequestBody Employee employee,@PathVariable int empId,@PathVariable int eDid) {
		  logger.info("Update Employee by EmployeeId.....");
	  employee.setEmpId(empId);
	  employee.seteDid(eDid);
	  employeeService.updateEmployee(employee,eDid,empId);
	  }
	  
	  
	  @DeleteMapping("/employees/deleteEmployee/{eDid}")
	  public void deleteEmployee(@PathVariable int eDid) {
		  logger.info("Delete Employee by DepartmentId....");
	  employeeService.deleteEmployee(eDid); 
	  }
	  
	  
	  @DeleteMapping("/employees/{eDid}/deleteEmployee/{empId}")
	  public void deleteSingleEmployee(@PathVariable int eDid,@PathVariable int empId) {
	  logger.info("Delete Single Employee by EmployeeId and DepartmentId... ");
	  employeeService.deleteSingleEmployee(eDid, empId); 
	  }
	  
	 
}


