package com.app.graphql.app.gql.resolver;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.app.graphql.app.domain.Address;
import com.app.graphql.app.domain.Employee;
import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class EmployeeResolver implements GraphQLResolver<Employee> {

    public List<Address> getAddressList(Employee emp) {
        return emp.getAddressList();
    }
    
    public Date registerDate(Employee emp) {
        return emp.getDob();
    }

}
