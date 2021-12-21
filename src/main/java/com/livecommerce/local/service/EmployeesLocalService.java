package com.livecommerce.local.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livecommerce.local.data.dao.EmployeesRepository;
import com.livecommerce.local.dto.RequestLocal;
import com.livecommerce.local.dto.ResponseLocal;
import com.livecommerce.local.model.EmployeesLocal;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeesLocalService {
    @Autowired
    private EmployeesRepository employeeRepository;

    public Either<ResponseLocal, List<EmployeesLocal>> postingEmployees(RequestLocal input) {
        ResponseLocal response = new ResponseLocal();
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<EmployeesLocal> employeesList = mapper.readValue(input.getBody(), new TypeReference<List<EmployeesLocal>>() {
            });
            if (Objects.isNull(employeesList) || employeesList.isEmpty()) {
                response.setStatusCode(500);
                response.setMessage("Data not Posted to DB due to error in convert data, invalid format data");
                return Either.left(response);
            }
            this.employeeRepository.saveAll(employeesList);
            return Either.right(employeesList);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Data not Posted to DB due to " + e.getMessage());
            return Either.left(response);
        }
    }

    public List<EmployeesLocal> createEmployee(List<EmployeesLocal> users) {
        return this.employeeRepository.saveAll(users);
    }

    public EmployeesLocal getEmployeeById(int id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    public List<EmployeesLocal> getEmployees() {
        return this.employeeRepository.findAll();
    }

    public EmployeesLocal updateEmployee(EmployeesLocal employee) {
        EmployeesLocal oldEmployee = null;
        Optional<EmployeesLocal> optionalEmployee = this.employeeRepository.findById(employee.getId());
        if (optionalEmployee.isPresent()) {
            oldEmployee = optionalEmployee.get();
            oldEmployee.setName(employee.getName());
            oldEmployee.setSalary(employee.getSalary());
            this.employeeRepository.save(oldEmployee);
        } else {
            return new EmployeesLocal();
        }
        return oldEmployee;
    }

    public String deleteEmployeeById(int id) {
        this.employeeRepository.deleteById(id);
        return "User got deleted";
    }
}