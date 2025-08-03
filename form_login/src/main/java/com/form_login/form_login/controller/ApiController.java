package com.form_login.form_login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ApiController {
    
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    
    @GetMapping("/api/public")
    public String getPublicEndpoint() {
        logger.info("Acesso ao endpoint público");
        return "Public endpoint accessed successfully!";
    }
    
    @GetMapping("/api/user")
    public String getUserEndpoint() {
        logger.info("Acesso ao endpoint de usuário");
        return "User endpoint accessed - requires USER role";
    }
    
    @GetMapping("/api/admin")
    public String getAdminEndpoint() {
        logger.info("Acesso ao endpoint de admin");
        return "Admin endpoint accessed - requires ADMIN role";
    }
    @GetMapping("/api/authenticated")
    public String getauthenticated() {
        logger.info("Acesso ao endpoint de authenticated");
        return "authenticated endpoint accessed - requires authenticated";
    }
    
}
