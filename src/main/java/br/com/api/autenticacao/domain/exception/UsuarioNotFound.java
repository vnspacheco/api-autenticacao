package br.com.api.autenticacao.domain.exception;

public class UsuarioNotFound extends RuntimeException {
    public UsuarioNotFound() {
        super();
    }

    public UsuarioNotFound(String mensagem) {
        super(mensagem);
    }

    public UsuarioNotFound(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public UsuarioNotFound(Throwable causa) {
        super(causa);
    }
}
