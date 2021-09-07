package com.SpringBootWebApi.service.impl;

import com.SpringBootWebApi.exception.ResourceNotExeption;
import com.SpringBootWebApi.model.Employee;
import com.SpringBootWebApi.repository.EmployeeRepository;
import com.SpringBootWebApi.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private EmployeeRepository employeeRepository;


    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if (employee.isPresent()){
//            return employee.get();
//        }else {
//            throw new ResourceNotExeption("Employee","Id",id);
//        }
//
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotExeption("Employee", "Id", id));


    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotExeption("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotExeption("Employee", "Id", id));
        employeeRepository.deleteById(id);
    }


}
