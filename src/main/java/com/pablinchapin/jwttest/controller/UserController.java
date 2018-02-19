/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.jwttest.controller;

import com.pablinchapin.jwttest.model.User;
import com.pablinchapin.jwttest.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pvargas
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;
    
    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public String registeredUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    
    @GetMapping("/get")
    public User getUser(HttpServletRequest request){
        ObjectId userId = (ObjectId) request.getAttribute("userId");
        
        return userService.getUser(userId);
    }
    
    
}
