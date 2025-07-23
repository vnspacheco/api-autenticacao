package br.com.api.autenticacao.adapters.servers.contract.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UsuarioCadastroResponse {

    private String login;
    private String senha;

}
