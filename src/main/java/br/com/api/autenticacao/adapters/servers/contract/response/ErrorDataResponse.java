package br.com.api.autenticacao.adapters.servers.contract.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDataResponse {

    private ErrorResponse data;

}