package br.com.api.autenticacao.adapters.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jwt.properties")
public class JwtConfiguration {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration}")
    private Integer expirationHours;

    public String getSecret() {
        return secret;
    }

    public String getIssuer() {
        return issuer;
    }

    public Integer getExpirationHours() {
        return expirationHours;
    }
}