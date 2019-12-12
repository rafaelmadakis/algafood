package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/por-nome")
     public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome) {

         return cozinhaRepository.findTodasByNomeContaining(nome);

     }

    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> cozinhaPorNome(String nome) {

        return cozinhaRepository.findByNome(nome);

    }


    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial
            , BigDecimal taxaFinal) {

        return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);

    }

//    @GetMapping("/restaurantes/por-nome")
//    public List<Restaurante> restaurantesPorTaxaFrete(String nome
//            , Long cozinhaId) {
//
//        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
//
//    }

    @GetMapping("/restaurantes/primeiro-por-nome")
    public Optional<Restaurante> restaurantePrimeiro(String nome) {

        return restauranteRepository.findFirstByNomeContaining(nome);

    }

    @GetMapping("/restaurantes/top2-por-nome")
    public List<Restaurante> restaurantesTop2porNome(String nome) {

        return restauranteRepository.findTop2ByNomeContaining(nome);

    }

    @GetMapping("/restaurantes/por-nome-e-frete")
    public List<Restaurante> restaurantesPorNOmeeFrete(String nome
    ,BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);

    }

    @GetMapping("/cozinhas/exists")
    public boolean cozinhaExist(String nome) {

        return cozinhaRepository.existsByNome(nome);

    }

    @GetMapping("/restaurantes/count-por-cozinha")
    public int restaurantesCountPorCozinha(Long cozinhaId) {

        return restauranteRepository.countByCozinhaId(cozinhaId);

    }

}
