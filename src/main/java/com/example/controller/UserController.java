package com.example.controller;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BaseController {
    @Autowired
    UserDao userDao;

    // localhost:8080/users
    @GetMapping("/users")
    public List<User> getUsers(){
        return userDao.findAll();
    }

    @PostMapping("createUser")
    public void createUser(@RequestBody User user){
        userDao.save(user);
    }
}
