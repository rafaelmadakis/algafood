package com.algaworks.algafood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeIdInput {

    @NotNull
    private Long id;

//    @NotBlank
//    private String nome;
//
//    @Valid
//    @NotNull
//    private EstadoIdInput estado;
}
