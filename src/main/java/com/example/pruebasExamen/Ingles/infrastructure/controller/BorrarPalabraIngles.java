package com.example.pruebasExamen.Ingles.infrastructure.controller;

import com.example.pruebasExamen.Ingles.application.InglesService;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesSimpleOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/ingles")

public class BorrarPalabraIngles {

    @Autowired
    private InglesService inglesService;

    @DeleteMapping("/{palabra}")
    public ResponseEntity<InglesSimpleOutputDto> BorrarPalabraIng(@PathVariable String palabra) {
        return  inglesService.deletePalabraIngles(palabra);
    }
}
