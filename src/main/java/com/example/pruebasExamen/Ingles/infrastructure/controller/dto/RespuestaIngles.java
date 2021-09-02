package com.example.pruebasExamen.Ingles.infrastructure.controller.dto;

import com.example.pruebasExamen.Ingles.domain.Ingles;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class RespuestaIngles {

    private HttpHeaders headers;

    private List<Ingles> body;

    private int status;

    public RespuestaIngles(List<Ingles> body, HttpHeaders headers, int status ){
        this.body = body;
        this.headers = headers;
        this.status = status;
    }

}
