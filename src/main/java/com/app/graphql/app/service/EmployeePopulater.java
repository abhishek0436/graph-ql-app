package com.app.graphql.app.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.graphql.app.domain.Address;
import com.app.graphql.app.domain.Employee;
import com.app.graphql.app.gql.util.DateUtil;
import com.app.graphql.app.repo.EmployeeRepo;

@Component
public class EmployeePopulater {
    
    @Autowired
    EmployeeRepo repo;
    
    @PostConstruct
    public void setupData() throws Exception {
        repo.save(populateEmployee());
    }
    
    private static Employee populateEmployee() throws ParseException {
        Employee employee = new Employee();
        employee.setName("Abhishek Kumar");
        employee.setDepartment("IT Dev");
        employee.setDob(DateUtil.parseDate("1984-01-04"));
        employee.setAddressList(buildAddresses(employee));
        return employee;
    }

    public static List<Address> buildAddresses(final Employee employee) {
        List<Address> addressList = new ArrayList<>();

        Address address = new Address();
        address.setCity("London");
        address.setCountry("United Kingdom");
        address.setCounty("Greater London");
        address.setStreet("Wembley");
        address.setPostcode("HA9 0BY");

        addressList.add(address);

        address = new Address();
        address.setCity("New Delhi");
        address.setCountry("India");
        address.setCounty("Delhi");
        address.setStreet("Mayur Vihar");
        address.setPostcode("1235647");

        addressList.add(address);

        employee.setAddressList(addressList);
        return addressList;
    }

}
