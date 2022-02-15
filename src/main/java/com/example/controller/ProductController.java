package com.example.controller;

import com.example.dao.ProductDao;
import com.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductDao productDao;

    @GetMapping("/product")
    public List<Product> getProduct(){

        return productDao.findAll();
    }
}
