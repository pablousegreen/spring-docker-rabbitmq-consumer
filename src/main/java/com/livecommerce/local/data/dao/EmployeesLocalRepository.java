package com.livecommerce.local.data.dao;

import com.livecommerce.local.model.EmployeesLocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesLocalRepository extends JpaRepository<EmployeesLocal, Integer> {

    List<EmployeesLocal> findByDept(String dept);
}
