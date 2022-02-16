package com.example.controller;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserDao userDao;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user) {
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
    public void delete(@PathVariable Integer id) {
        userDao.deleteById(id);
    }

    @GetMapping("/getuser/{phone}")
    public List<User> getUserByPhone(@PathVariable String phone) {
        return userDao.findByPhone(phone);
    }

    @GetMapping("/getname/{fullName}")
    public List<User> getUserByName(@PathVariable String fullName) {
        return userDao.findName(fullName);
    }
}
