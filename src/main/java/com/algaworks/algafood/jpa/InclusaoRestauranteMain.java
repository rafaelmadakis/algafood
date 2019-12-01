package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Comida Mineira");
        restaurante1.setTaxaFrete(BigDecimal.valueOf(12.00));

//        Cozinha cozinha2 = new Cozinha();
//        cozinha2.setNome("Japonesa");

       // cozinha1 = cozinhaRepository.adicionar(cozinha1);
        restaurante1 = restauranteRepository.salvar(restaurante1);

        System.out.printf("%d - %s\n", restaurante1.getId(), restaurante1.getNome());
//        System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());

    }

}
