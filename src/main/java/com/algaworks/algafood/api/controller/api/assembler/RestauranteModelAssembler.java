package com.algaworks.algafood.api.controller.api.assembler;

import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method extrated toModel
     * @param restaurante
     * @return
     */
    public RestauranteModel toModel(Restaurante restaurante) {

        // mapeamento setter, getter
        return modelMapper.map(restaurante, RestauranteModel.class);
    }

    //Converter de um tipo para outro
    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {

        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }
}
