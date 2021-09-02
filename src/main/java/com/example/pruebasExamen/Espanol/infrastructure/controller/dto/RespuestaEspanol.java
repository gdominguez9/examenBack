package com.example.pruebasExamen.Espanol.infrastructure.controller.dto;

import com.example.pruebasExamen.Espanol.domain.Espanol;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class RespuestaEspanol {

    private HttpHeaders headers;

    private List<Espanol> body;

    private int status;

    public RespuestaEspanol(List<Espanol> body, HttpHeaders headers, int status ){
        this.body = body;
        this.headers = headers;
        this.status = status;
    }

}
