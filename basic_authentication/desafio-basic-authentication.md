# 🔐 Desafio: Basic Authentication com Spring Security

## 📋 Objetivo
Implementar autenticação básica (Basic Authentication) em uma aplicação Spring Boot, criando diferentes níveis de acesso para usuários e administradores.

## 🎯 Requisitos do Desafio

### 1. Configuração de Segurança
- [ ] Configurar Spring Security com Basic Authentication
- [ ] Criar usuários em memória com diferentes roles
- [ ] Implementar diferentes níveis de autorização

### 2. Usuários e Roles
Criar os seguintes usuários:
- **user**: senha `password123`, role `USER`
- **admin**: senha `admin123`, roles `USER` e `ADMIN`
- **guest**: senha `guest123`, role `GUEST`

### 3. Endpoints Protegidos
Criar os seguintes endpoints com suas respectivas proteções:

#### Endpoints Públicos (sem autenticação)
- `GET /api/public` - Retorna "Endpoint público - acesso liberado"

#### Endpoints que requerem autenticação
- `GET /api/authenticated` - Retorna "Acesso autenticado realizado" (qualquer usuário logado)

#### Endpoints com role USER
- `GET /api/user` - Retorna "Área do usuário" (requer role USER ou ADMIN)
- `POST /api/user/profile` - Retorna "Perfil do usuário atualizado"

#### Endpoints com role ADMIN
- `GET /api/admin` - Retorna "Área administrativa" (requer role ADMIN)
- `GET /api/admin/users` - Retorna lista de usuários
- `DELETE /api/admin/users/{id}` - Remove um usuário

#### Endpoints negados para GUEST
- `GET /api/restricted` - Deve ser negado para role GUEST

### 4. Configurações Adicionais
- [ ] Personalizar página de erro 401 (Unauthorized)
- [ ] Personalizar página de erro 403 (Forbidden)
- [ ] Configurar logout
- [ ] Adicionar headers de segurança básicos

## 🧪 Testes Manuais

### Teste 1: Acesso Público
```bash
curl -X GET http://localhost:8080/api/public
# Esperado: 200 OK - "Endpoint público - acesso liberado"
```

### Teste 2: Acesso Não Autenticado
```bash
curl -X GET http://localhost:8080/api/user
# Esperado: 401 Unauthorized
```

### Teste 3: Autenticação com USER
```bash
curl -X GET http://localhost:8080/api/user \
  -u user:password123
# Esperado: 200 OK - "Área do usuário"
```

### Teste 4: Acesso Negado para GUEST
```bash
curl -X GET http://localhost:8080/api/user \
  -u guest:guest123
# Esperado: 403 Forbidden
```

### Teste 5: Acesso de ADMIN
```bash
curl -X GET http://localhost:8080/api/admin \
  -u admin:admin123
# Esperado: 200 OK - "Área administrativa"
```

## 🔧 Estrutura de Arquivos Esperada

```
src/main/java/com/basic_authentication/basic_authentication/
├── BasicAuthenticationApplication.java
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── PublicController.java
│   ├── UserController.java
│   └── AdminController.java
└── model/
    └── User.java (opcional)
```

## 💡 Dicas de Implementação

### SecurityConfig.java
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configure here
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        // Configure users in memory
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCrypt or NoOp for simplicity
    }
}
```

### Autorização por URL
- `/api/public/**` - permitAll()
- `/api/authenticated/**` - authenticated()
- `/api/user/**` - hasRole("USER")
- `/api/admin/**` - hasRole("ADMIN")
- `/api/restricted/**` - hasRole("USER") and not hasRole("GUEST")

## 🏆 Critérios de Avaliação

### Básico (⭐)
- Configuração básica do Spring Security funcionando
- Pelo menos 2 usuários diferentes configurados
- Endpoints públicos e protegidos funcionando

### Intermediário (⭐⭐)
- Múltiplas roles configuradas corretamente
- Todos os endpoints com autorização adequada
- Testes manuais passando

### Avançado (⭐⭐⭐)
- Configurações de segurança personalizadas
- Tratamento de erros customizado
- Código bem estruturado e documentado
- Headers de segurança configurados

## 🚀 Bonus Points

- [ ] Implementar endpoint para listar usuários logados
- [ ] Adicionar logging de tentativas de acesso
- [ ] Criar endpoint para trocar senha
- [ ] Implementar rate limiting básico
- [ ] Adicionar testes unitários para os endpoints

## 📚 Recursos Úteis

- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [Basic Authentication Guide](https://www.baeldung.com/spring-security-basic-authentication)
- [HTTP Status Codes](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)

---

**Tempo estimado:** 2-4 horas  
**Dificuldade:** Intermediário  
**Pré-requisitos:** Conhecimento básico de Spring Boot e conceitos de autenticação
