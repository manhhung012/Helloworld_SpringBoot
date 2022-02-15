package com.example.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    private Integer id;
    private String fullName;
    private String phone;
}