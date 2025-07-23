package br.com.api.autenticacao.adapters.database.mapper.request;

import br.com.api.autenticacao.adapters.database.entity.Usuario;

public interface UsuarioEntityRequestMapper {
    Usuario execute(final br.com.api.autenticacao.domain.entity.Usuario usuario, final String senha);
}
