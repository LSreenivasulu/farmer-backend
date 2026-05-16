package com.example.farmer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    // ✅ Password Encoder (keep this)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Security Configuration (FULLY OPEN FOR DEVELOPMENT)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // 🔥 Disable CSRF (IMPORTANT for POST, PUT, DELETE)
                .csrf(csrf -> csrf.disable())

                // 🔥 Disable CORS inside Spring Security (we handle via @CrossOrigin)
                .cors(cors -> cors.disable())

                // 🔥 Allow ALL requests (NO AUTH REQUIRED)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // 🔥 Disable default login popup
                .httpBasic(basic -> basic.disable())

                // 🔥 Disable form login
                .formLogin(form -> form.disable());

        return http.build();
    }
}