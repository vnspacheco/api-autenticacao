package br.com.api.autenticacao.adapters.servers.mapper.request;

import br.com.api.autenticacao.adapters.servers.contract.request.LoginRequest;
import br.com.api.autenticacao.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class LoginRequestMapperImpl implements LoginRequestMapper {

    @Override
    public Usuario execute(LoginRequest loginRequest) {
        return Usuario.builder()
                .login(loginRequest.getLogin())
                .senha(loginRequest.getSenha())
                .build();
    }
}
