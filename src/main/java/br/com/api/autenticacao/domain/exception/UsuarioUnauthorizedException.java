package br.com.api.autenticacao.domain.exception;

public class UsuarioUnauthorizedException extends RuntimeException {
    public UsuarioUnauthorizedException() {
        super();
    }

    public UsuarioUnauthorizedException(String mensagem) {
        super(mensagem);
    }

    public UsuarioUnauthorizedException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public UsuarioUnauthorizedException(Throwable causa) {
        super(causa);
    }
}
