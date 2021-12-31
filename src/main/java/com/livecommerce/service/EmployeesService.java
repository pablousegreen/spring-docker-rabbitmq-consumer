package com.livecommerce.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livecommerce.data.dao.EmployeesRepository;
import com.livecommerce.data.dao2.EmployeesTwoRepository;
import com.livecommerce.dto.Request;
import com.livecommerce.dto.Response;
import com.livecommerce.model.Employees;
import com.livecommerce.model2.Employees2;
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

    @Autowired
    private EmployeesTwoRepository employeeTwoRepository;

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
            //employeesList.forEach(e -> this.employeeRepository.save(e));
            this.employeeRepository.saveAll(employeesList);
            List<Employees2> employees2List = mapper.readValue(input.getBody(), new TypeReference<List<Employees2>>() {});
            this.employeeTwoRepository.saveAll(employees2List);
            return Either.right(employeesList);
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Data not Posted to DB due to "+e.getMessage());
                    return Either.left(response);
        }
    }

    public Optional<List<Employees>> postingEmployeesOptional(Request input){
        Response response = new Response();
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Employees> employeesList = mapper.readValue(input.getBody(), new TypeReference<List<Employees>>() {});
            if(Objects.isNull(employeesList) || employeesList.isEmpty()){
                response.setStatusCode(500);
                response.setMessage("Data not Posted to DB due to error in convert data, invalid format data");
                return Optional.empty();
            }
            //employeesList.forEach(e -> this.employeeRepository.save(e));
            this.employeeRepository.saveAll(employeesList);
            return Optional.of(employeesList);
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Data not Posted to DB due to "+e.getMessage());
            return Optional.empty();
        }
    }

    public List<Employees> createEmployee(List<Employees> users) {
        return this.employeeRepository.saveAll(users);
    }

    public Employees getEmployeeById(String id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    public List<Employees> getEmployees() {
        return this.employeeRepository.findAll();
    }

    public Optional<Employees> updateEmployee(Employees employee) {
        Employees oldEmployee=null;
        Optional<Employees> optionalEmployee=this.employeeRepository.findById(employee.getId());
        if(optionalEmployee.isPresent()) {
            oldEmployee=optionalEmployee.get();
            oldEmployee.setName(employee.getName());
            oldEmployee.setSalary(employee.getSalary());
            this.employeeRepository.save(oldEmployee);
        }else {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public String deleteEmployeeById(String id) {
        this.employeeRepository.deleteById(id);
        return "User got deleted";
    }
}
