package com.example.services;

import com.example.dao.ProductDao;
import com.example.model.Product;
import com.example.model.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;
    @Override
    public List<Product> getAllProduct() {
        return productDao.findAll();
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return productDao.findById(id);
    }

    @Override
    public ResponseEntity<ResponseObject> deleteProduct(Integer id) {
        boolean exists = productDao.existsById(id);
        if (exists) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Delete Product successfully", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Failed", "Cannot find Product to delete", ""));
        }
    }
}
