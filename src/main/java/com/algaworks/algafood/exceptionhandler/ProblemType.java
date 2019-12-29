package com.algaworks.algafood.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    MENSAGEM_INCOMPREENCIVEL("/mensagem-incompreencivel", "Mensagem incompreencível"),
    RECURSO_NAO_ENCONTRAO("/recurso-nao-encontrado", "Recurso não encontrado"),
    EMTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");


    private String title;
    private String uri;

    ProblemType(String path, String title) {

        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
