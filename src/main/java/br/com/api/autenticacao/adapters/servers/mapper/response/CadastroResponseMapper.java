package br.com.api.autenticacao.adapters.servers.mapper.response;

import br.com.api.autenticacao.adapters.servers.contract.response.UsuarioCadastroResponse;
import br.com.api.autenticacao.domain.entity.Usuario;

public interface CadastroResponseMapper {
    UsuarioCadastroResponse execute(final Usuario usuario);
}
