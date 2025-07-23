package br.com.api.autenticacao.adapters.servers.controller;

import br.com.api.autenticacao.adapters.servers.contract.request.CadastroRequest;
import br.com.api.autenticacao.adapters.servers.contract.request.LoginRequest;
import br.com.api.autenticacao.adapters.servers.contract.response.UsuarioCadastroResponse;
import br.com.api.autenticacao.adapters.servers.mapper.request.CadastroRequestMapper;
import br.com.api.autenticacao.adapters.servers.mapper.request.LoginRequestMapper;
import br.com.api.autenticacao.adapters.servers.mapper.response.CadastroResponseMapper;
import br.com.api.autenticacao.application.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
@AllArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;
    private final LoginRequestMapper loginRequestMapper;
    private final CadastroRequestMapper cadastroRequestMapper;
    private final CadastroResponseMapper cadastroResponseMapper;

    @PostMapping("/cadastros")
    public ResponseEntity<UsuarioCadastroResponse> cadastrar(@RequestBody CadastroRequest cadastroRequest) {
        return ResponseEntity.ok(cadastroResponseMapper.execute(usuarioService.cadastrar(cadastroRequestMapper.execute(cadastroRequest))));
    }

    @PostMapping("/autenticacoes")
    public ResponseEntity<String> autenticar(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(usuarioService.autenticar(loginRequestMapper.execute(loginRequest)).getToken());
    }
}
