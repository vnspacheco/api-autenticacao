package br.com.api.autenticacao.adapters.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name = "scope")
    private String scope;

}
