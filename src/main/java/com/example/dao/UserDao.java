package com.example.dao;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    List<User> findByPhone(String phone);

    @Query("select u from User u where u.fullName = ?1")
    List<User> findName(String fullName);
}
