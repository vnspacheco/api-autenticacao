package br.com.api.autenticacao.adapters.database.mapper.response;

import br.com.api.autenticacao.adapters.database.entity.Usuario;

public interface UsuarioEntityResponseMapper {
    br.com.api.autenticacao.domain.entity.Usuario execute(final Usuario usuario, final String token);
}
