package com.example.services;

import com.example.model.ResponseObject;
import com.example.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();

    Optional<User> getUserById(Integer id);

    ResponseEntity<ResponseObject> createUser(User user);

    ResponseEntity<ResponseObject> updateUser(User userForm, Integer id);

    ResponseEntity<ResponseObject> deleteUser(Integer id);

}
