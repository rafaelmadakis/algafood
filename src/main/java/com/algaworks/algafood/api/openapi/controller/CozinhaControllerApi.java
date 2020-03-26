package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Cozinhas")
public interface CozinhaControllerApi {

    @ApiOperation("Lista as cozinhas com paginação")
    public PagedModel<CozinhaModel> listar(Pageable pegeable);

    @ApiOperation("Busca uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID de cozinha inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    public CozinhaModel buscar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
                                           Long cozinhaId);


    @ApiOperation("Cadastra uma cozinha")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cozinha cadastrada"),
    })
    public CozinhaModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cozinha",
    required = true)
            CozinhaInput cozinhaInput);


    @ApiOperation("Atualiza uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cozinha Atualizada"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    public CozinhaModel atualizar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
                                 Long cozinhaId,

                                  @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados")
                                  CozinhaInput cozinhaInput);



    public void remover(@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
            Long cozinhaId);
}
