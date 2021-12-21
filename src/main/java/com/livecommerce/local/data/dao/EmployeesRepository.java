package com.livecommerce.local.data.dao;

import com.livecommerce.local.model.EmployeesLocal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<EmployeesLocal, Integer> {

    List<EmployeesLocal> findByDept(String dept);
}
