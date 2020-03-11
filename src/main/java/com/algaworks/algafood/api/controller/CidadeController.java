package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.api.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;


@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController  implements CidadeControllerOpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;


    @GetMapping
    public CollectionModel<CidadeModel> listar() {
        List<Cidade> todasCidades = cidadeRepository.findAll();

        List<CidadeModel> cidadesModel = cidadeModelAssembler.toCollectionModel(todasCidades);

        cidadesModel.forEach(cidadeModel -> {
            cidadeModel.add(linkTo(methodOn(CidadeController.class)
                    .buscar(cidadeModel.getId())).withSelfRel());

            cidadeModel.add(linkTo(methodOn(CidadeController.class)
                    .listar()).withRel("cidades"));

            cidadeModel.add(linkTo(methodOn(EstadoController.class)
                    .buscar(cidadeModel.getEstado().getId())).withSelfRel());
        });

        CollectionModel<CidadeModel>  cidadesCollectionModel = new CollectionModel<>(cidadesModel);

        cidadesCollectionModel.add(linkTo(CidadeController.class).withSelfRel());

        return cidadesCollectionModel;
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);

        //cidadeModel.add(new Link("http://api.algafood.local:8080/cidades/1", IanaLinkRelations.SELF));
        //cidadeModel.add(new Link("http://api.algafood.local:8080/cidades", IanaLinkRelations.COLLECTION));


        cidadeModel.add(linkTo(methodOn(CidadeController.class)
                .buscar(cidadeModel.getId())).withSelfRel());

//        cidadeModel.add(linkTo(CidadeController.class)
//        .slash(cidadeModel.getId()).withSelfRel());


        cidadeModel.add(linkTo(methodOn(CidadeController.class)
                .listar()).withRel("cidades"));

//        cidadeModel.add(linkTo(CidadeController.class)
//        .withRel("cidades"));

//        cidadeModel.add(new Link("http://api.algafood.local:8080/cidades/1"));
//        cidadeModel.add(new Link("http://api.algafood.local:8080/cidades", "cidades"));

        cidadeModel.add(linkTo(methodOn(EstadoController.class)
                .buscar(cidadeModel.getEstado().getId())).withSelfRel());

//        cidadeModel.getEstado().add(linkTo(EstadoController.class)
//        .slash(cidadeModel.getEstado().getId()).withSelfRel());

//        cidadeModel.add(new Link("http://api.algafood.local:8080/estados/1"));

        return cidadeModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            cidade = cadastroCidade.salvar(cidade);

            CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);

            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());

            return cidadeModel;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable Long cidadeId,
                                 @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

            cidadeAtual = cadastroCidade.salvar(cidadeAtual);

            return cidadeModelAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);
    }

}