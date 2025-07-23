package br.com.api.autenticacao.adapters.servers.mapper.response;

import br.com.api.autenticacao.adapters.servers.contract.response.UsuarioAutenticacaoDataResponse;
import br.com.api.autenticacao.adapters.servers.contract.response.UsuarioAutenticacaoResponse;
import br.com.api.autenticacao.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class AutenticacaoResponseMapperImpl implements AutenticacaoResponseMapper {

    @Override
    public UsuarioAutenticacaoDataResponse execute(Usuario usuario) {
        return UsuarioAutenticacaoDataResponse.builder()
                .data(UsuarioAutenticacaoResponse.builder()
                        .token(usuario.getToken())
                        .build())
                .build();
    }
}
