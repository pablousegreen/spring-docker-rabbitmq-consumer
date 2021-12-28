package com.livecommerce.model2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Document(collection="Employees2")
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employees2 {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String dept;
    private double salary;
}