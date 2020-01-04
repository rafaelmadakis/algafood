package com.algaworks.algafood.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private List<Object> objects;

    private String userMessage;
    private LocalDateTime timestamp;

    @Getter
    @Builder
    public static class Object {

        private String name;
        private String userMessage;
    }

//    private LocalDateTime dataHora;
//    private String mensagem;
}
