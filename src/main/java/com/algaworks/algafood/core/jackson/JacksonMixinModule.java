package com.algaworks.algafood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

/**
 * registry serializable and dessiralizable of Jackson
 */

@Component
public class JacksonMixinModule  extends SimpleModule {

    public JacksonMixinModule() {
//        setMixInAnnotation(Cidade.class, CidadeMixin.class);
//        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
