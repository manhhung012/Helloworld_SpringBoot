package com.example.controller;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserDao userDao;

    // localhost:8080/users
    @GetMapping("/users")
    public List<User> getUsers(){
        return userDao.findAll();
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        userDao.save(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User userForm, @PathVariable Integer id) {

        return userDao.findById(id)
                .map(user -> {
                    user.setFullName(userForm.getFullName());
                    user.setPhone(userForm.getPhone());
                    return userDao.save(user);
                })
                .orElseGet(() -> {
                    userForm.setId(id);
                    return userDao.save(userForm);
                });
    }
    
    @DeleteMapping("/delete/{id}")
    public void update(@PathVariable Integer id){
        userDao.deleteById(id);
    }
}
