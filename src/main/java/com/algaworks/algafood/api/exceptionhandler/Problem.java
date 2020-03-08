package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400")
    private Integer status;

    @ApiModelProperty(example = "https://algafood.com.br/dados-invalidos")
    private String type;

    @ApiModelProperty(example = "Dados inválidos", position = 1)
    private String title;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
    private String detail;

    @ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opcional)")
    private List<Object> objects;


    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
    private String userMessage;

    @ApiModelProperty(example = "2020-01-01T18:08:02.70844Z")
    private OffsetDateTime timestamp;

    @ApiModel("ObjetoProblema")
    @Getter
    @Builder
    public static class Object {

        @ApiModelProperty(example = "preço")
        private String name;

        @ApiModelProperty(example = "O Preço é obrigatório")
        private String userMessage;
    }

//    private OffsetDateTime dataHora;
//    private String mensagem;
}
