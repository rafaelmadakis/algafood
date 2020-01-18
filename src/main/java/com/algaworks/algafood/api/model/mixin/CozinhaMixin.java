package com.algaworks.algafood.api.model.mixin;

import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class CozinhaMixin {

    private Long id;


    private String nome;

    @JsonIgnore
    private List<Restaurante> restaurantes = new ArrayList<>();
}
