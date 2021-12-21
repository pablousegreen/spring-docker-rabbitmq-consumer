package com.livecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Employees")
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employees {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String dept;
    private double salary;
}