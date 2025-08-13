package br.com.api.autenticacao.adapters.database.mapper.response;

import br.com.api.autenticacao.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEntityResponseMapperImpl implements UsuarioEntityResponseMapper {

    @Override
    public Usuario execute(br.com.api.autenticacao.adapters.database.entity.Usuario usuario, String token) {
        return Usuario.builder()
                .login(usuario.getLogin())
                .senha(usuario.getSenha())
                .scope(usuario.getScope())
                .token(token)
                .build();
    }
}
