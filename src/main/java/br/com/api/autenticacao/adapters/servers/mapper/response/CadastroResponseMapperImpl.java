package br.com.api.autenticacao.adapters.servers.mapper.response;

import br.com.api.autenticacao.adapters.servers.contract.response.UsuarioCadastroDataResponse;
import br.com.api.autenticacao.adapters.servers.contract.response.UsuarioCadastroResponse;
import br.com.api.autenticacao.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class CadastroResponseMapperImpl implements CadastroResponseMapper {

    @Override
    public UsuarioCadastroDataResponse execute(Usuario usuario) {
        return UsuarioCadastroDataResponse.builder()
                .data(UsuarioCadastroResponse.builder()
                        .login(usuario.getLogin())
                        .senha(usuario.getSenha())
                        .build())
                .build();
    }
}
