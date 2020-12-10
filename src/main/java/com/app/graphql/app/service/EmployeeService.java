package com.app.graphql.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.graphql.app.domain.Employee;
import com.app.graphql.app.repo.EmployeeRepo;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee createEmployee(Employee emp) {
        return employeeRepo.save(emp);
    }

    public Optional<Employee> findEmployee(long id) {
        return employeeRepo.findById(id);
    }

    public int countAddresses(long id) {
        Optional<Employee> employee = findEmployee(id);
        if (employee.isPresent() && !employee.get().getAddressList().isEmpty()) {
            return employee.get().getAddressList().size();
        }
        return 0;
    }
    
    public Iterable<Employee> allEmployees() {
        return employeeRepo.findAll();
    }

}
