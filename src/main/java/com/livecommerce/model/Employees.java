package com.livecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Employees")
public class Employees {
    @Id
    private String id;
    private String name;
    private String dept;
    private Double salary;

    public Employees(String name, String dept, Double salary){
        this.name = name;
        this.dept = dept;
        this.salary= salary;
    }
}