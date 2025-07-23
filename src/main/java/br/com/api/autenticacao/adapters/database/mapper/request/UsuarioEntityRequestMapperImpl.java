package br.com.api.autenticacao.adapters.database.mapper.request;

import br.com.api.autenticacao.adapters.database.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEntityRequestMapperImpl implements UsuarioEntityRequestMapper {

    @Override
    public Usuario execute(br.com.api.autenticacao.domain.entity.Usuario usuario) {
        return Usuario.builder()
                .login(usuario.getLogin())
                .senha(usuario.getSenha())
                .build();
    }
}
