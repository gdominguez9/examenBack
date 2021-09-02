package com.example.pruebasExamen.Espanol.infrastructure.controller;

import com.example.pruebasExamen.Espanol.application.EspanolService;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output.EspanolSimpleOutputDto;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.RespuestaEspanol;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/espanol")
public class BorrarEspanol {

    @Autowired
    private EspanolService espanolService;

    @DeleteMapping
    public ResponseEntity<EspanolSimpleOutputDto> BorrarEsp(){ //throws Exception {

        RespuestaEspanol respuestaEspanol = espanolService.deleteEspanol();

        return new ResponseEntity<EspanolSimpleOutputDto>(null, respuestaEspanol.getHeaders(),respuestaEspanol.getStatus());
    }

}
