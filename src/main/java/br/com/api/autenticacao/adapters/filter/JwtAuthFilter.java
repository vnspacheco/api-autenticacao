package br.com.api.autenticacao.adapters.filter;

import br.com.api.autenticacao.adapters.beans.JwtConfiguration;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtConfiguration jwtConfiguration;
    private final ObjectMapper objectMapper;

    public JwtAuthFilter(JwtConfiguration jwtConfiguration, ObjectMapper objectMapper) {
        this.jwtConfiguration = jwtConfiguration;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Ignora as rotas de autenticação e cadastro
        if (request.getServletPath().contains("/api/v1/usuarios/autenticacoes") || 
            request.getServletPath().contains("/api/v1/usuarios/cadastros") ||
            request.getServletPath().contains("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Verifica se o token está presente no header Authorization
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            sendErrorResponse(response, "Token não fornecido", HttpStatus.UNAUTHORIZED);
            return;
        }

        try {
            // Extrai o token do header
            String token = authorizationHeader.substring("Bearer ".length());
            
            // Verifica o token
            Algorithm algorithm = Algorithm.HMAC256(jwtConfiguration.getSecret());
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer(jwtConfiguration.getIssuer())
                    .build()
                    .verify(token);
            
            // Extrai o login e scope do token
            String login = decodedJWT.getSubject();
            String scope = decodedJWT.getClaim("scope").asString();
            
            // Continua a cadeia de filtros
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            sendErrorResponse(response, "Token inválido: " + e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    private void sendErrorResponse(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", status.value());
        errorDetails.put("message", message);
        
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("data", errorDetails);
        
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}