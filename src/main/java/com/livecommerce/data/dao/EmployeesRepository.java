package com.livecommerce.data.dao;

import com.livecommerce.model.Employees;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeesRepository extends MongoRepository<Employees, Integer> {

}
