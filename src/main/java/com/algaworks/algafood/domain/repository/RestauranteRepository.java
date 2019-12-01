package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> todos();
    Restaurante buscar(Long Id);
    Restaurante salvar(Restaurante restaurante);
    void remover(Restaurante restaurante);
}
