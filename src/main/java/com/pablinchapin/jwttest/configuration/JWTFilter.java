/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.jwttest.configuration;

import com.pablinchapin.jwttest.service.TokenService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author pvargas
 */
@Configuration
public class JWTFilter extends GenericFilterBean{
    
    private TokenService tokenService;
    
    @Autowired
    JWTFilter(TokenService tokenService){
        this.tokenService = tokenService;
    }
    
     @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = request.getHeader("Authorization");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.sendError(HttpServletResponse.SC_OK, "success");
            return;
        }

        if (allowRequestWithoutToken(request)) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req, res);
        } else {
            if (token == null || !tokenService.isTokenValid(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                ObjectId userId = new ObjectId(tokenService.getUserIdFromToken(token));
                request.setAttribute("userId", userId);
                filterChain.doFilter(req, res);

            }
        }

    }

    public boolean allowRequestWithoutToken(HttpServletRequest request) {
        if(request.getRequestURI().contains("/register")) {
            return true;
        }
        return false;
    }
    
    
}
