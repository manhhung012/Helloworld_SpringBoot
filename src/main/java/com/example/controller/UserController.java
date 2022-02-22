package com.example.controller;

import com.example.model.ResponseObject;
import com.example.model.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/getUserById/{id}")
    public Optional<User> getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @PostMapping("/createUser")
    public ResponseEntity<ResponseObject> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody User userForm, @PathVariable Integer id) {
        return userService.updateUser(userForm,id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
