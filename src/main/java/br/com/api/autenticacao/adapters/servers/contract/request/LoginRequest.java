package br.com.api.autenticacao.adapters.servers.contract.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    @Pattern(regexp = "^[a-zA-Z]+$", message = "O campo login só pode ter letras")
    @Size(max = 15, message = "O campo login só pode no máximo ter 15 letras")
    private String login;

    @Size(max = 15, message = "O campo senha só pode ter 15 caracteres no máximo")
    private String senha;
    
    @Size(max = 30, message = "O campo scope só pode ter 30 caracteres no máximo")
    private String scope;
}
