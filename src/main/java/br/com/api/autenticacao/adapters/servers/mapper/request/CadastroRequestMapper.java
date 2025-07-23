package br.com.api.autenticacao.adapters.servers.mapper.request;

import br.com.api.autenticacao.adapters.servers.contract.request.CadastroRequest;
import br.com.api.autenticacao.domain.entity.Usuario;

public interface CadastroRequestMapper {
    Usuario execute(final CadastroRequest cadastroRequest);
}
