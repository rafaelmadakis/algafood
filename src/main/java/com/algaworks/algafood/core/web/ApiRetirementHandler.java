package com.algaworks.algafood.core.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiRetirementHandler extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (request.getRequestURI().startsWith("/v1/")) {
            response.addHeader("X-Algafood-Deprecated", "Essa versão da API está depreciada e deixara de existir a partir de 19/10/2021"
             + " Use a versão mais atual da API");

           // response.setStatus(HttpStatus.GONE.value());
            //return false;
        }

        return true;
    }
}
