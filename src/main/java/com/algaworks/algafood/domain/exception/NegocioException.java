package com.algaworks.algafood.domain.exception;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST)//, reason = "Entidade não encontrada")
public class NegocioException extends RuntimeException {

    public NegocioException(String mensagem) {
        super(mensagem);
    }

    public NegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
