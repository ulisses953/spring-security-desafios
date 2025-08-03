package com.basic_authentication.basic_authentication.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class BasicAuthenticationController {
    
    private static final Logger logger = LoggerFactory.getLogger(BasicAuthenticationController.class);
    
    // Endpoints Públicos (sem autenticação)
    @GetMapping("/api/public")
    public String getPublicEndpoint(Authentication auth) {
        logger.info("Acesso ao endpoint público");
        logger.info("Usuário autenticado: {}", auth != null ? auth.getName() : "Nenhum usuário autenticado");
        logger.info("Autoridades: {}", auth != null ? auth.getAuthorities() : "Nenhuma autoridade");
        return "Endpoint público - acesso liberado";
    }
    
    // Endpoints que requerem autenticação
    @GetMapping("/api/authenticated")
    public String getAuthenticatedEndpoint() {
        logger.info("Acesso ao endpoint autenticado");
        return "Acesso autenticado realizado";
    }
    
    // Endpoints com role USER
    @GetMapping("/api/user")
    public String getUserEndpoint() {
        logger.info("Acesso ao endpoint de usuário");
        return "Área do usuário";
    }
    
    @PostMapping("/api/user/profile")
    public String updateUserProfile() {
        logger.info("Atualização de perfil do usuário");
        return "Perfil do usuário atualizado";
    }
    
    // Endpoints com role ADMIN
    @GetMapping("/api/admin")
    public String getAdminEndpoint() {
        logger.info("Acesso ao endpoint de admin");
        return "Área administrativa";
    }
    
    @GetMapping("/api/admin/users")
    public String getAdminUsers() {
        logger.info("Listagem de usuários pelo admin");
        return "Lista de usuários: [user, admin, guest]";
    }
    
    @DeleteMapping("/api/admin/users/{id}")
    public String deleteUser(@PathVariable String id) {
        logger.info("Remoção do usuário: {}", id);
        return "Usuário " + id + " removido com sucesso";
    }
    
    // Endpoint restrito para GUEST
    @GetMapping("/api/restricted")
    public String getRestrictedEndpoint() {
        logger.info("Acesso ao endpoint restrito");
        return "Área restrita - não acessível para GUEST";
    }
    
}
