package br.com.api.autenticacao.adapters.servers.mapper.request;

import br.com.api.autenticacao.adapters.servers.contract.request.LoginRequest;
import br.com.api.autenticacao.domain.entity.Usuario;

public interface LoginRequestMapper {
    Usuario execute(final LoginRequest loginRequest);
}
