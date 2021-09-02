package com.example.pruebasExamen.Espanol.infrastructure.controller;

import com.example.pruebasExamen.Espanol.application.EspanolService;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output.EspanolOutputDto;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.RespuestaEspanol;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/espanol")
public class ConsultarPalabraEspanol {

    @Autowired
    private EspanolService espanolService;

    @GetMapping("/{palabra}")
    public ResponseEntity<EspanolOutputDto> ConsultaPalabraEsp(@PathVariable String palabra){

        RespuestaEspanol respuestaEspanol = espanolService.getPalabraEspanol(palabra);

        EspanolOutputDto espanolOutputDto = new EspanolOutputDto(respuestaEspanol.getBody().get(0));

        return new ResponseEntity<EspanolOutputDto>(espanolOutputDto,respuestaEspanol.getHeaders(),respuestaEspanol.getStatus());
    }

}
