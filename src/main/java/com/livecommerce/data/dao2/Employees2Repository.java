package com.livecommerce.data.dao2;

import com.livecommerce.model.Employees;
import com.livecommerce.model2.Employees2;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Employees2Repository extends MongoRepository<Employees2, Integer> {

}
