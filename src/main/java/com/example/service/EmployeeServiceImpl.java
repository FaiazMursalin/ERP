package com.example.service;

import com.example.controller.EmployeeNotFoundException;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    @Override
    public void saveEmployee(Employee employee){
        this.employeeRepository.save(employee);
    }
    @Override
    public Employee getEmployeeById(long id) throws EmployeeNotFoundException {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee= null;
        if(optional.isPresent()){
            employee = optional.get();
        }else{
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public void deleteEmployee(long id){
        this.employeeRepository.deleteById(id);
    }
    @Override
    public Employee findByName(String name){
        return this.employeeRepository.findByName(name);
    }

}
