package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.controller.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.controller.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;


    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @GetMapping
    public List<RestauranteModel> listar() {

        return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {

        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return restauranteModelAssembler.toModel(restaurante);
    }


//    @PostMapping
//    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
//        try {
//            restaurante = cadastroRestaurante.salvar(restaurante);
//
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body(restaurante);
//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.badRequest()
//                    .body(e.getMessage());
//        }
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(
            @RequestBody @Valid RestauranteInput restauranteInput) {
        try {

            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
        } catch (CozinhaNaoEncontradoException | CidadeNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

//    @PutMapping("/{restauranteId}")
//    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
//                                       @RequestBody Restaurante restaurante) {
//
//        //Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
//        try {
//            Restaurante restauranteAtual = restauranteRepository.findById(restauranteId)
//                    .orElse(null);
//
//            if (restauranteAtual != null) {
//                BeanUtils.copyProperties(restaurante, restauranteAtual,
//                        "id", "formasPagamento", "endereco", "dataCadastro");
//
//                restauranteAtual = cadastroRestaurante.salvar(restaurante);
//
//                return ResponseEntity.ok(restauranteAtual);
//            }
//            return ResponseEntity.notFound().build();
//
//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.badRequest()
//                    .body(e.getMessage());
//        }
//
//    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
                                 @RequestBody @Valid RestauranteInput restauranteInput) {

//        Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

        restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
        //        BeanUtils.copyProperties(restaurante, restauranteAtual,
//                "id", "formaPagamento", "endereco", "dataCadastro", "produtos");
        try {
            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradoException | CidadeNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }


//    @PatchMapping("/{restauranteId}")
//    public Restaurante atualizarParcial(@PathVariable Long restauranteId,
//                                        @RequestBody Map<String, Object> campos, HttpServletRequest request) {
//        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
//
//        merge(campos, restauranteAtual, request);
//
//        validate(restauranteAtual, "restaurante");
//        return toModel(atualizar(restauranteId, restauranteAtual);
//
//    }

    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);

        validator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidacaoException(bindingResult);
        }
    }

    private void merge(@RequestBody Map<String, Object> dadosOrigem, Restaurante restauranteDestino
            , HttpServletRequest request) {

        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);


            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                // API Reflection Spring
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true); // quebrar acesso privado do Model;

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

//            System.out.println(nomePropriedade + " = " + valorPropriedade);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);

            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }


    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        cadastroRestaurante.ativar(restauranteId);

    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
        cadastroRestaurante.inativar(restauranteId);

    }


}

