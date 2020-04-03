package com.algaworks.algafood.api.v2.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CozinhaInputV2 {

    @ApiModelProperty(example = "Brasileira", required = true)
    private String nomeCozinha;
}
