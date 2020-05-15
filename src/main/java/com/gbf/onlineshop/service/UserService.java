package com.gbf.onlineshop.service;

import com.gbf.onlineshop.test.CheckFukingReflection;
import com.gbf.onlineshop.model.User;
import com.gbf.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    public CheckFukingReflection get(){
        return new CheckFukingReflection(101L, new User("login", "pass", "mail"));
    }
}
