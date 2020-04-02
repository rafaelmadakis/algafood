package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

   @ApiOperation("Lista os usuários responsaáveis associados a restaurantes")
   @ApiResponses({
           @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
   })
    public CollectionModel<UsuarioModel> listar(
            @ApiParam(value = "ID do restaurantes", example = "1", required = true)
            Long restauranteId);


   @ApiOperation("Desassociação de restaurante com usuário responsável")
   @ApiResponses({
           @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
           @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class)
   })
    public ResponseEntity<Void> desassociar(

            @ApiParam(value = "ID do restaurante", example = "1", required = true)
            Long restauranteId,

            @ApiParam(value = "ID do usuário", example = "1", required = true)
            Long usuarioId);


    @ApiOperation("Associação de restaurante com usuário responsável")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado",
            response = Problem.class)
    })
    public ResponseEntity<Void> associar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
            Long restauranteId,

            @ApiParam(value = "ID do usuário", example = "1", required = true)
            Long usuarioId);
}
