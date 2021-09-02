package com.example.pruebasExamen.Espanol.infrastructure.controller;

import com.example.pruebasExamen.Espanol.application.EspanolService;
import com.example.pruebasExamen.Espanol.domain.Espanol;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output.EspanolOutputDto;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.RespuestaEspanol;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/espanol/")
public class ConsultaEspanol {

    @Autowired
    private EspanolService espanolService;

    @GetMapping
    public ResponseEntity<List<EspanolOutputDto>> ConsultaEsp(){

        RespuestaEspanol respuestaEspanol = espanolService.getEspanol();

        List<EspanolOutputDto> espanolOutputDtoList = new ArrayList<>();

        for (Espanol espanol : respuestaEspanol.getBody()){
            espanolOutputDtoList.add(new EspanolOutputDto(espanol));
        }

        return new ResponseEntity<List<EspanolOutputDto>>(espanolOutputDtoList,respuestaEspanol.getHeaders(),respuestaEspanol.getStatus());
    }


}
