package com.example.controller;

import com.example.model.Product;
import com.example.model.ResponseObject;
import com.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/getProductById/{id}")
    public Optional<Product> getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/deletePro/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Integer id) {
       return productService.deleteProduct(id);
    }
}
