package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separadaos por vírgula",
                    name = "campos", paramType = "query", type = "string")

    })
    @ApiOperation("Pesquisa de pedidos")
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

  @ApiOperation("Registra um pedido")
  @ApiResponses({
          @ApiResponse(code = 201, message = "Pedido registrado")
  })
    public PedidoModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true)
            PedidoInput pedidoInput);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separadaos por vírgula",
                    name = "campos", paramType = "query", type = "string")

    })
    public PedidoModel buscar(
            @ApiParam(value = "Código de um pedido",example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true)
            String codigoPedido);

}
