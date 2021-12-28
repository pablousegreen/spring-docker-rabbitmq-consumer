package com.livecommerce.data.dao;

import com.livecommerce.model.Employees;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends MongoRepository<Employees, Integer> {

}
