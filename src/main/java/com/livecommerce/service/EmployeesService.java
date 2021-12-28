package com.livecommerce.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livecommerce.data.dao.EmployeesRepository;
import com.livecommerce.dto.Request;
import com.livecommerce.dto.Response;
import com.livecommerce.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vavr.control.Either;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepository employeeRepository;

    /*public EmployeesService(EmployeesRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }*/

    public Either<Response, List<Employees>> postingEmployees(Request input){
        Response response = new Response();
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Employees> employeesList = mapper.readValue(input.getBody(), new TypeReference<List<Employees>>() {});
            if(Objects.isNull(employeesList) || employeesList.isEmpty()){
                response.setStatusCode(500);
                response.setMessage("Data not Posted to DB due to error in convert data, invalid format data");
                return Either.left(response);
            }
            this.employeeRepository.saveAll(employeesList);
            return Either.right(employeesList);
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Data not Posted to DB due to "+e.getMessage());
            return Either.left(response);
        }
    }

    public List<Employees> createEmployee(List<Employees> users) {
        return this.employeeRepository.saveAll(users);
    }

    public Employees getEmployeeById(int id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    public List<Employees> getEmployees() {
        return this.employeeRepository.findAll();
    }

    public Employees updateEmployee(Employees employee) {
        Employees oldEmployee=null;
        Optional<Employees> optionalEmployee=this.employeeRepository.findById(employee.getId());
        if(optionalEmployee.isPresent()) {
            oldEmployee=optionalEmployee.get();
            oldEmployee.setName(employee.getName());
            oldEmployee.setSalary(employee.getSalary());
            this.employeeRepository.save(oldEmployee);
        }else {
            return new Employees();
        }
        return oldEmployee;
    }

    public String deleteEmployeeById(int id) {
        this.employeeRepository.deleteById(id);
        return "User got deleted";
    }
}
