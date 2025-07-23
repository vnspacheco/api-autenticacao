package br.com.api.autenticacao.domain.exception;

public class UsuarioExistingException extends RuntimeException {
    public UsuarioExistingException() {
        super();
    }

    public UsuarioExistingException(String mensagem) {
        super(mensagem);
    }

    public UsuarioExistingException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public UsuarioExistingException(Throwable causa) {
        super(causa);
    }
}
