package br.com.api.autenticacao.adapters.servers.mapper.request;

import br.com.api.autenticacao.adapters.servers.contract.request.CadastroRequest;
import br.com.api.autenticacao.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class CadastroRequestMapperImpl implements CadastroRequestMapper {

    @Override
    public Usuario execute(CadastroRequest cadastroRequest) {
        return Usuario.builder()
                .login(cadastroRequest.getLogin())
                .senha(cadastroRequest.getSenha())
                .scope(cadastroRequest.getScope())
                .build();
    }
}
