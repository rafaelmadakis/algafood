package com.algaworks.algafood.api.v1.apenapi;

import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("RestaurantesBasicoModel")
@Data
public class RestaurantesBasicoModelOpenApi {

    private RestaurantesEmbeddedModelOpenApi _embedded;
    private Links _links;

    public class RestaurantesEmbeddedModelOpenApi{

        private List<RestauranteBasicoModel> restaurantes;
    }

}
