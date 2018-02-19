/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.jwttest.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

/**
 *
 * @author pvargas
 */
@Service
public class TokenService {
    
    public static final String TOKEN_SECRET = "s4T2zOIWHMM1sxq";
    
    public String createToken(ObjectId userId){
        
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            
            String token = JWT.create()
                            .withClaim("userId", userId.toString())
                            .withClaim("createdAt", new Date())
                            .sign(algorithm);
            
            return token;
        
        }catch(UnsupportedEncodingException exception){
            
            exception.printStackTrace();
            //LOG wrong encoding message
        
        }catch(JWTCreationException exception){
        
            exception.printStackTrace();
            //LOG token signing failed
        }
        
    
        return null;
    }
    
    
    public String getUserIdFromToken(String token){
        
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            
            JWTVerifier verifier = JWT.require(algorithm)
                                        .build();
            
            DecodedJWT jwt = verifier.verify(token);
            
            return jwt.getClaim("userId").asString();
        }catch(UnsupportedEncodingException exception){
        
            exception.printStackTrace();
            //LOG wrong encoding message
        }catch(JWTVerificationException exception){
            exception.printStackTrace();
            //LOG token verification failed
        }
        
        return null;
    
    }
    
    public boolean isTokenValid(String token){
        
        String userId = this.getUserIdFromToken(token);
        return userId != null;
    }
    
}
