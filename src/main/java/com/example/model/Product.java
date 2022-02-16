package com.example.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Product")
public class Product {
    @Id
    private Integer id;
    private String fullName;
    private String idUser;
}
