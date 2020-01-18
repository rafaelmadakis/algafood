package com.algaworks.algafood.api.model.mixin;

import com.algaworks.algafood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CidadeMixin {



    private  Long id;

    private String nome;

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
