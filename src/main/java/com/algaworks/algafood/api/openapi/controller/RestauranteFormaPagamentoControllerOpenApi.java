package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {


    @ApiOperation("Lista as formas de pagamentos associadas a restaurante")
    @ApiResponses({
            @ApiResponse(code = 404, message = "restaurante não encontrado", response = Problem.class)
    })
    public List<FormaPagamentoModel> listar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
            Long restauranteId);


    @ApiOperation("Desassociação de restaurante com forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante our forma de pagamento não encontrado",
            response = Problem.class)
    })
    public void desassociar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
            Long restauranteId,

            @ApiParam(value = "ID da forma d epagamento", example = "1", required = true)
            Long formaPagamentoId);


    @ApiOperation("Associação de restaurantes com forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação reslizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado",
             response = Problem.class)
    })
    public void associar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
            Long restauranteId,

            @ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
            Long formaPagamentoId);
}
