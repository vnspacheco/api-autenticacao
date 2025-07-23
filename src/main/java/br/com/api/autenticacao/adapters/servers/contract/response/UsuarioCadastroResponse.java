package br.com.api.autenticacao.adapters.servers.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioCadastroResponse {

    private String login;
    private String senha;

}
