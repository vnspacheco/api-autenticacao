package br.com.api.autenticacao.domain.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException() {
        super();
    }

    public UsuarioNotFoundException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public UsuarioNotFoundException(Throwable causa) {
        super(causa);
    }
}
