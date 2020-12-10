package com.app.graphql.app.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.graphql.app.domain.Employee;
import com.app.graphql.app.service.EmployeeService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private EmployeeService employeeService;

    public Employee findEmployee(int id) {
        if(employeeService.findEmployee(id).isPresent()) {
            return employeeService.findEmployee(id).get();  
        }
        return null;
        
    }

    public int countAddress(int id) {
        return employeeService.countAddresses(id);
    }

    public Iterable<Employee> allEmployee() {
        return employeeService.allEmployees();
    }

}
