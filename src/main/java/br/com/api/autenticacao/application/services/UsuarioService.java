package br.com.api.autenticacao.application.services;

import br.com.api.autenticacao.domain.entity.Usuario;

public interface UsuarioService {
    Usuario autenticar(Usuario usuario);
    Usuario cadastrar(Usuario usuario);
}
