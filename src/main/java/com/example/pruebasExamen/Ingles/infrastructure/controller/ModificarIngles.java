package com.example.pruebasExamen.Ingles.infrastructure.controller;

import com.example.pruebasExamen.Ingles.application.InglesService;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.input.InglesInputDto;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/ingles")
public class ModificarIngles {

    @Autowired
    private InglesService inglesService;

    @PutMapping("/{palabra}")
    public ResponseEntity<InglesOutputDto> ModificarIng(@RequestBody InglesInputDto inglesInputDto, @PathVariable String palabra){
        return inglesService.updateIngles(inglesInputDto,palabra);
    }

}
