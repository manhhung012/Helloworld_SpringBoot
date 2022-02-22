package com.example.services;

import com.example.dao.UserDao;
import com.example.model.ResponseObject;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> getAllUser() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public ResponseEntity<ResponseObject> createUser(User user) {
        List<User> listName = userDao.findByFullName(user.getFullName());
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

    @Override
    public ResponseEntity<ResponseObject> updateUser(User userForm, Integer id) {
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

    @Override
    public ResponseEntity<ResponseObject> deleteUser(Integer id) {
        boolean exists = userDao.existsById(id);
        if (exists) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Delete User successfully", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Failed", "Cannot find User to delete", ""));
        }
    }
}
