package br.com.api.autenticacao.domain.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private String login;
    private String senha;
    private String token;
    private String scope;
}
