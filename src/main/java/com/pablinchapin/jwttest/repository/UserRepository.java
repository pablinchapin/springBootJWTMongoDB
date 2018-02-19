/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.jwttest.repository;

import com.pablinchapin.jwttest.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pvargas
 */
@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{
    
}
