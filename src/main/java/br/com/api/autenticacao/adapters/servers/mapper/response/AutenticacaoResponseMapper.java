package br.com.api.autenticacao.adapters.servers.mapper.response;

import br.com.api.autenticacao.adapters.servers.contract.response.UsuarioAutenticacaoDataResponse;
import br.com.api.autenticacao.domain.entity.Usuario;

public interface AutenticacaoResponseMapper {
    UsuarioAutenticacaoDataResponse execute(final Usuario usuario);
}
