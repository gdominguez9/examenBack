package com.example.pruebasExamen.Ingles.infrastructure.controller;

import com.example.pruebasExamen.Ingles.application.InglesService;
import com.example.pruebasExamen.Ingles.domain.Ingles;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesOutputDto;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.RespuestaIngles;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ingles")
public class ConsultarIngles {

    @Autowired
    private InglesService inglesService;

    @GetMapping
    public ResponseEntity<List<InglesOutputDto>> ConsultaIng(){

        RespuestaIngles respuestaIngles = inglesService.getIngles();

        List<InglesOutputDto> inglesOutputDtoList = new ArrayList<>();

        for (Ingles ingles : respuestaIngles.getBody()){
            inglesOutputDtoList.add(new InglesOutputDto(ingles));
        }

        return new ResponseEntity<List<InglesOutputDto>>(inglesOutputDtoList,respuestaIngles.getHeaders(),respuestaIngles.getStatus());
    }

}
