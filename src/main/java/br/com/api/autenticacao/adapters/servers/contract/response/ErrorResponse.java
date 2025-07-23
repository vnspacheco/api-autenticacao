package br.com.api.autenticacao.adapters.servers.contract.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;

}
