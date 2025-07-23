package br.com.api.autenticacao.domain.exception;

public class UsuarioInvalid extends RuntimeException {
    public UsuarioInvalid() {
        super();
    }

    public UsuarioInvalid(String mensagem) {
        super(mensagem);
    }

    public UsuarioInvalid(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public UsuarioInvalid(Throwable causa) {
        super(causa);
    }
}
