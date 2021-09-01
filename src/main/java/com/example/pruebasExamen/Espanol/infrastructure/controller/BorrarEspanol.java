package com.example.pruebasExamen.Espanol.infrastructure.controller;

import com.example.pruebasExamen.Espanol.application.EspanolService;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesSimpleOutputDto;
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
    public ResponseEntity<InglesSimpleOutputDto> BorrarEsp(){ //throws Exception {
        return espanolService.deleteEspanol();
    }

}
