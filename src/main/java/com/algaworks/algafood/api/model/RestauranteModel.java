package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/*
Class DTO Restaurante
 */
@Setter
@Getter
public class RestauranteModel {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaModel cozinha;
    private Boolean ativo;

}
