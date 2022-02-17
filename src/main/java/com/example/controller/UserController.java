package com.example.controller;

import com.example.dao.UserDao;
import com.example.model.ResponseObject;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseObject> createUser(@RequestBody User user) {
        List<User> listName = userDao.findName(user.getFullName());
        if (!listName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "User name already taken", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Create User successfully", userDao.save(user))
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody User userForm, @PathVariable Integer id) {

        userDao.findById(id).map(user -> {
                    user.setFullName(userForm.getFullName());
                    user.setPhone(userForm.getPhone());
                    return userDao.save(user);
                })
                .orElseGet(() -> {
                    userForm.setId(id);
                    return userDao.save(userForm);
                });

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Update User successfully", ""));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable Integer id) {
        boolean exists = userDao.existsById(id);
        if (exists) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Delete User successfully", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Failed", "Cannot find User to delete", ""));
        }
    }

    @GetMapping("/getuser/{phone}")
    public ResponseEntity<ResponseObject> getUserByPhone(@PathVariable String phone) {
        List<User> listUser = userDao.findByPhone(phone);
        if (!listUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query user sucessfully", listUser)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find with phone: " + phone, "")
            );
        }
    }

    @GetMapping("/getname/{fullName}")
    public List<User> getUserByName(@PathVariable String fullName) {
        return userDao.findName(fullName);
    }
}
