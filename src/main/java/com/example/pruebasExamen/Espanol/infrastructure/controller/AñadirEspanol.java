package com.example.pruebasExamen.Espanol.infrastructure.controller;

import com.example.pruebasExamen.Espanol.application.EspanolService;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output.EspanolSimpleOutputDto;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.input.EspanolnputDto;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.RespuestaEspanol;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/espanol")
public class AñadirEspanol {

    @Autowired
    private EspanolService espanolService;

    @PostMapping
    public ResponseEntity<EspanolSimpleOutputDto> AñadirEsp(@Valid @RequestBody EspanolnputDto espanolnputDto){

        RespuestaEspanol respuestaEspanol = espanolService.saveEspanol(espanolnputDto);

        if (respuestaEspanol.getStatus()!=202) {
            return new ResponseEntity<EspanolSimpleOutputDto>(null,respuestaEspanol.getHeaders(),respuestaEspanol.getStatus());
        }

        EspanolSimpleOutputDto espanolSimpleOutputDto = new EspanolSimpleOutputDto(respuestaEspanol.getBody().get(0));
        return new ResponseEntity<EspanolSimpleOutputDto>(espanolSimpleOutputDto,respuestaEspanol.getHeaders(),respuestaEspanol.getStatus());

    }

}
