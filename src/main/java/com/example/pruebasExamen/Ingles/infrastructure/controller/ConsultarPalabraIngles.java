package com.example.pruebasExamen.Ingles.infrastructure.controller;

import com.example.pruebasExamen.Ingles.application.InglesService;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/ingles")
public class ConsultarPalabraIngles {

    @Autowired
    private InglesService inglesService;

    @GetMapping("/{palabra}")
    public ResponseEntity<InglesOutputDto> ConsultaPalabraIng(@PathVariable String palabra){
        return inglesService.getPalabraIngles(palabra);
    }

}
