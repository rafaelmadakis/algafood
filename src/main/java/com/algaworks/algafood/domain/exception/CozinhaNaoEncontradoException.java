package com.algaworks.algafood.domain.exception;

public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException {

    public CozinhaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public CozinhaNaoEncontradoException(Long cozinhaId) {

        this(String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
    }
}
