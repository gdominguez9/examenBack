package com.example.pruebasExamen.Espanol.infrastructure.controller;

import com.example.pruebasExamen.Espanol.application.EspanolService;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output.EspanolOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/espanol/")
public class ConsultaEspanol {

    @Autowired
    private EspanolService espanolService;

    @GetMapping
    public ResponseEntity<List<EspanolOutputDto>> ConsultaEsp(){
        return espanolService.getEspanol();
    }


}
