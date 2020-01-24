package com.algaworks.algafood.api.controller.api.assembler;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    //converter
    public Restaurante toDomainObject(RestauranteInput restauranteInput) {

      return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {

        /**
         * @See RestauranteController.class
         * @Method atualizar()
         */
        restaurante.setCozinha(new Cozinha());

        if (restaurante.getEndereco() != null){
            restaurante.getEndereco().setCidade(new Cidade());
        }

        modelMapper.map(restauranteInput, restaurante);
    }

}