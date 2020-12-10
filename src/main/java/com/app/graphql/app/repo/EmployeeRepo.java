package com.app.graphql.app.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.graphql.app.domain.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {

}
