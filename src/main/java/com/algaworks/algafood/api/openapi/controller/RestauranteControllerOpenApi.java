package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.apenapi.model.RestauranteBasicoModelApi;
import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

        @ApiOperation(value = "Lista restaurantes", response = RestauranteBasicoModelApi.class)
        @ApiImplicitParams({
                @ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
                        name = "projecao", paramType = "query", type = "string")

        })
//        @JsonView(RestauranteView.Resumo.class)
        @GetMapping
         CollectionModel<RestauranteBasicoModel> listar();

        @ApiOperation(value = "Lista restaurantes", hidden = true)
        public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();

        @ApiOperation("Buscar um restaurante por ID")
        @ApiResponses({
                @ApiResponse(code = 400, message = "ID do restaurante invpalido", response = Problem.class),
                @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
        })
        public RestauranteModel buscar(
                @ApiParam(value = "ID de um restaurante", example = "1", required = true)
                Long restauranteId);

        @ApiOperation("Cadastra um restaurante")
        @ApiResponses({
                @ApiResponse(code = 201, message = "Restaurante cadastrado")
        })
        public RestauranteModel adicionar(
                @ApiParam(name = "corpo", value = "Representação de um novo restaurante", required = true)
                RestauranteInput restauranteInput);


        @ApiOperation("Atualiza um restaurante por ID")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Restaurante atualizado"),
                @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
        })
        public RestauranteModel atualizar(
                @ApiParam(value = "ID de um retaurante", example = "1", required = true)
                Long restauranteId,

                @ApiParam(name = "corpo", value = "Representação de restaurante com o novos dados"
                        , required = true)
                RestauranteInput restauranteInput);


        @ApiOperation("Ativa um restaurante por ID")
        @ApiResponses({
                @ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
                @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
        })
        public ResponseEntity<Void> ativar(
                @ApiParam(value = "ID de um restaurante", example = "1", required = true)
                Long restauranteId);


        @ApiOperation("Inativa um restaurante por ID")
        @ApiResponses({
                @ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
                @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
        })
        public ResponseEntity<Void> inativar(
                @ApiParam(value = "ID de um restaurante", example = "1", required = true)
                Long restauranteId);


        @ApiOperation("Ativa múltiplos restaurantes")
        @ApiResponses({
                @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
        })
        public void ativarMultiplos(
                @ApiParam(name = "corpo", value = "IDs de restaurantes", required = true)
                List<Long> restaurantesIds);


        @ApiOperation("Inativa múltiplos restaurantes")
        @ApiResponses({
                @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
        })
        public void inativarMultiplos(
                @ApiParam(name = "corpo", value = "IDs de restaurantes", required = true)
                List<Long> restaurantesIds);


        @ApiOperation("Abre um restaurante por ID")
        @ApiResponses({
                @ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
                @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
        })
        public ResponseEntity<Void> abrir(
                @ApiParam(value = "Id de um restaurante", example = "1", required = true)
                Long restauranteId);


        @ApiOperation("Fecha um restaurante por iD")
        @ApiResponses({
                @ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
                @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
        })
        public ResponseEntity<Void> fechar(
                @ApiParam(value = "ID de um retaurante", example = "1", required = true)
                Long restauranteId);
}
