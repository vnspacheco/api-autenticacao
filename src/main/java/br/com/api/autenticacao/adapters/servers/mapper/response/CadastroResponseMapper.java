package br.com.api.autenticacao.adapters.servers.mapper.response;

import br.com.api.autenticacao.adapters.servers.contract.response.UsuarioCadastroDataResponse;
import br.com.api.autenticacao.domain.entity.Usuario;

public interface CadastroResponseMapper {
    UsuarioCadastroDataResponse execute(final Usuario usuario);
}
