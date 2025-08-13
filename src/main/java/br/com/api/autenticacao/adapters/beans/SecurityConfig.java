package br.com.api.autenticacao.adapters.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/v1/usuarios/autenticacoes",
                "/api/v1/usuarios/cadastros",
                "/h2-console/**").permitAll()
                .anyRequest().permitAll() // Alterado para permitAll para deixar o JwtAuthFilter gerenciar a autenticação
            .and()
            .headers().frameOptions().disable(); // Necessário para o H2 Console

        return http.build();
    }
}