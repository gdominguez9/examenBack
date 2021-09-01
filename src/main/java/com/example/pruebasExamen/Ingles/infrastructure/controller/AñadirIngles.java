package com.example.pruebasExamen.Ingles.infrastructure.controller;

import com.example.pruebasExamen.Ingles.application.InglesService;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.input.InglesInputDto;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/ingles")
public class AñadirIngles {

    @Autowired
    private InglesService inglesService;

    @PostMapping
    public ResponseEntity<InglesOutputDto> AñadirIng(@Valid @RequestBody InglesInputDto inglesInputDto){
        return inglesService.saveIngles(inglesInputDto);
    }

}
