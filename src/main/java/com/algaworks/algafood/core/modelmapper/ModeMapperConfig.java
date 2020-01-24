package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModeMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelmapper =  new ModelMapper();

//        modelmapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//                .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setTaxaFrete);

        var enderecoToEnderecoModelTypeMap = modelmapper.createTypeMap(
                Endereco.class, EnderecoModel.class);

            enderecoToEnderecoModelTypeMap.<String>addMapping(endereco -> endereco.getCidade()
                 .getEstado().getNome(),
                (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

            return modelmapper;

    }
}
