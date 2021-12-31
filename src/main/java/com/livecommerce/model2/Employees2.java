package com.livecommerce.model2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Employees2")
public class Employees2 {
    @Id
    private String id;
    private String name;
    private String dept;
    private Double salary;

    public Employees2(String name, String dept, Double salary){
        this.name = name;
        this.dept = dept;
        this.salary= salary;
    }
}