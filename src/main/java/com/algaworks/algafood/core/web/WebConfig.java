package com.algaworks.algafood.core.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;


@Configuration
public class WebConfig  implements WebMvcConfigurer {

    /**
     * Habililtar CORS Globalmente
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        //.allowedMethods("GET", "HEAD", "POST")
          .allowedMethods("*");
//                .maxAge(30);
    }

    @Bean
    public Filter ShallowETagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }
}
