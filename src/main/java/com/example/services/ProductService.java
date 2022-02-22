package com.example.services;

import com.example.model.Product;
import com.example.model.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProduct();

    Optional<Product> getProductById(Integer id);

    ResponseEntity<ResponseObject> deleteProduct(Integer id);
}
