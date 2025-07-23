package br.com.api.autenticacao.adapters.servers.contract.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    private String login;
    private String senha;

}
