package dev.gravity.eis.openapi.service;


import dev.gravity.eis.openapi.entity.Department;
import dev.gravity.eis.openapi.entity.Employee;
import dev.gravity.eis.openapi.entity.Gender;
import dev.gravity.eis.openapi.mapper.EmployeeMapper;
import dev.gravity.eis.openapi.repository.DepartmentRepository;
import dev.gravity.eis.openapi.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return  employeeRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found with id :" + id) );
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmpDepartment(Integer emp_id , Integer dept_id) {
        Employee employee = employeeRepository.findById(emp_id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found with id : "+emp_id) );

        Department department = departmentRepository.findById(dept_id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Department not found with id : "+emp_id) );;

        employee.setDepartment(department);
        employeeRepository.save(employee);

        return employee;
    }

    public List<Employee> findEmployeesByGender(Gender gender) {
        return employeeRepository.findByGender(gender);
    }

    public  List<Employee> searchEmployeesByGender(Gender gender) {
        return employeeRepository.searchByGender(gender);
    }

    public Employee updateEmployee(Employee employee) {
        employeeRepository.findById(employee.getId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found with id : "+employee.getId()) );
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
         employeeRepository.deleteById(id);
    }
}

