package com.example.pruebasExamen.Ingles.infrastructure.controller;

import com.example.pruebasExamen.Ingles.application.InglesService;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ingles")
public class ConsultarIngles {

    @Autowired
    private InglesService inglesService;

    @GetMapping
    public ResponseEntity<List<InglesOutputDto>> ConsultaIng(){
        return inglesService.getIngles();
    }

}
