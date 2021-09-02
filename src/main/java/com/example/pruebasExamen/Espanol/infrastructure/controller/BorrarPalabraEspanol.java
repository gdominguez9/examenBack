package com.example.pruebasExamen.Espanol.infrastructure.controller;

import com.example.pruebasExamen.Espanol.application.EspanolService;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output.EspanolSimpleOutputDto;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.RespuestaEspanol;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/espanol")
public class BorrarPalabraEspanol {

    @Autowired
    private EspanolService espanolService;

    @DeleteMapping("/{palabra}")
    public ResponseEntity<EspanolSimpleOutputDto> BorrarPalabraEsp(@PathVariable String palabra){

        RespuestaEspanol respuestaEspanol = espanolService.deletePalabraEspanol(palabra);

        return new ResponseEntity<EspanolSimpleOutputDto>(null, respuestaEspanol.getHeaders(),respuestaEspanol.getStatus());
    }

}
