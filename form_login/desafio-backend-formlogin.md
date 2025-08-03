
# 💻 Desafio Back-end: Autenticação com Spring Security (formLogin)

## 🎯 Objetivo

Criar uma **API back-end segura** utilizando Spring Boot + Spring Security com autenticação baseada em **formulário (formLogin)**. A aplicação deve gerenciar sessões autenticadas com base em `HttpSession`.

---

## ✅ Requisitos do Desafio

### 🔐 Segurança

- [ ] Usar `formLogin` como método de autenticação
- [ ] Criar dois usuários em memória:
  - `admin / admin123` com **ROLE_ADMIN**
  - `user / user123` com **ROLE_USER**
- [ ] Proteger os endpoints da API para que apenas usuários autenticados possam acessá-los
- [ ] Restringir acesso com base nas roles

---

## 📦 Endpoints Esperados

| Método | URL           | Acesso        | Descrição                         |
|--------|---------------|---------------|-----------------------------------|
| GET    | /api/public   | Público       | Acesso aberto                     |
| GET    | /api/user     | ROLE_USER     | Somente usuários com ROLE_USER    |
| GET    | /api/admin    | ROLE_ADMIN    | Somente usuários com ROLE_ADMIN   |
| GET    | /api/home     | Autenticado   | Qualquer usuário autenticado      |

---

## 🧱 Estrutura de Projeto Sugerida

```
src/
├── config/
│   └── SecurityConfig.java
├── controller/
│   └── ApiController.java
└── DemoApplication.java
```

---

## ⚙️ Exemplo de Configuração (SecurityConfig)

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/public").permitAll()
            .requestMatchers("/api/user").hasRole("USER")
            .requestMatchers("/api/admin").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin(withDefaults()) // ativa login com formulário padrão
        .logout(logout -> logout.logoutSuccessUrl("/api/public"));
    return http.build();
}

@Bean
public UserDetailsService users() {
    UserDetails user = User.withUsername("user")
        .password(passwordEncoder().encode("user123"))
        .roles("USER")
        .build();
    UserDetails admin = User.withUsername("admin")
        .password(passwordEncoder().encode("admin123"))
        .roles("ADMIN")
        .build();
    return new InMemoryUserDetailsManager(user, admin);
}

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

---

## 📚 Tecnologias Sugeridas

- Java 17+
- Spring Boot 3+
- Spring Security
- Maven ou Gradle

---

## 🏁 Critérios de Conclusão

- [ ] Login baseado em formulário funciona corretamente
- [ ] Sessão de usuário criada após login
- [ ] Acesso aos endpoints protegido por autenticação e roles
- [ ] Logout encerra a sessão e redireciona para `/api/public`

---

## 🚀 Boa sorte no desafio!
