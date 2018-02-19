/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.jwttest.service;

import com.pablinchapin.jwttest.model.User;
import com.pablinchapin.jwttest.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pvargas
 */
@Service
public class UserService {
    
    private UserRepository userRepository;
    private TokenService tokenService;
    
    
    @Autowired
    UserService(UserRepository userRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }
    
    
    public User getUser(ObjectId userId){
        return userRepository.findOne(userId);
    }
    
    public String saveUser(User user){
        User savedUser = userRepository.save(user);
        
        return tokenService.createToken(savedUser.getId());
    }
    
    
}
