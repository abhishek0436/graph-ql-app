package com.app.graphql.app.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.graphql.app.domain.Employee;
import com.app.graphql.app.service.EmployeeService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private EmployeeService employeeService;

    public Employee createEmployee(Employee employee) {
        employeeService.createEmployee(employee);
        return employee;
    }

}
