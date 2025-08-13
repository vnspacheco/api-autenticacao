package br.com.api.autenticacao.adapters.servers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/teste")
public class TesteController {

    @GetMapping
    public ResponseEntity<Map<String, String>> helloWorld() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World! Este endpoint só pode ser acessado com um token JWT válido.");
        return ResponseEntity.ok(response);
    }
}