package com.livecommerce.local.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesLocal {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String dept;
    private double salary;
}