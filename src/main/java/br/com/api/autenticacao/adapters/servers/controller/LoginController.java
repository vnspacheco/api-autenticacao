package br.com.api.autenticacao.adapters.servers.controller;

import br.com.api.autenticacao.adapters.servers.contract.request.CadastroRequest;
import br.com.api.autenticacao.adapters.servers.contract.request.LoginRequest;
import br.com.api.autenticacao.adapters.servers.contract.response.UsuarioAutenticacaoDataResponse;
import br.com.api.autenticacao.adapters.servers.contract.response.UsuarioCadastroDataResponse;
import br.com.api.autenticacao.adapters.servers.mapper.request.CadastroRequestMapper;
import br.com.api.autenticacao.adapters.servers.mapper.request.LoginRequestMapper;
import br.com.api.autenticacao.adapters.servers.mapper.response.AutenticacaoResponseMapper;
import br.com.api.autenticacao.adapters.servers.mapper.response.CadastroResponseMapper;
import br.com.api.autenticacao.application.services.UsuarioService;
import br.com.api.autenticacao.domain.entity.Usuario;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final AutenticacaoResponseMapper autenticacaoResponseMapper;

    @PostMapping("/cadastros")
    public ResponseEntity<UsuarioCadastroDataResponse> cadastrar(@Valid @RequestBody CadastroRequest cadastroRequest) {
        Usuario usuario = cadastroRequestMapper.execute(cadastroRequest);

        usuario = usuarioService.cadastrar(usuario);

        UsuarioCadastroDataResponse response = cadastroResponseMapper.execute(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/autenticacoes")
    public ResponseEntity<UsuarioAutenticacaoDataResponse> autenticar(@Valid @RequestBody LoginRequest loginRequest) {
        Usuario usuario = loginRequestMapper.execute(loginRequest);

        usuario = usuarioService.autenticar(usuario);

        UsuarioAutenticacaoDataResponse response = autenticacaoResponseMapper.execute(usuario);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
