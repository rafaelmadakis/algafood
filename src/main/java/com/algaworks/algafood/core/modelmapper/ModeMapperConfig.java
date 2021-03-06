package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.v1.model.EnderecoModel;
import com.algaworks.algafood.api.v1.model.input.ItemPedidoInput;
import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModeMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelmapper =  new ModelMapper();

        modelmapper.createTypeMap(CidadeInputV2.class, Cidade.class)
                .addMappings(mapper -> mapper.skip(Cidade::setId));

//        modelmapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//                .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setTaxaFrete);

        modelmapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var enderecoToEnderecoModelTypeMap = modelmapper.createTypeMap(
                Endereco.class, EnderecoModel.class);

            enderecoToEnderecoModelTypeMap.<String>addMapping(endereco -> endereco.getCidade()
                 .getEstado().getNome(),
                (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

            return modelmapper;

    }
}
