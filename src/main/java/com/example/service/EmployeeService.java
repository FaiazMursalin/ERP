package com.example.service;

import com.example.controller.EmployeeNotFoundException;
import com.example.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id) throws EmployeeNotFoundException;
    void deleteEmployee(long id);
    Employee findByName(String name);
}
