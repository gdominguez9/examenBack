package com.example.pruebasExamen.Espanol.infrastructure.controller;

import com.example.pruebasExamen.Espanol.application.EspanolService;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output.EspanolSimpleOutputDto;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.input.EspanolnputDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/espanol")
public class ModificarEspanol {

    @Autowired
    private EspanolService espanolService;

    @PutMapping("/{palabra}")
    public ResponseEntity<EspanolSimpleOutputDto> ModificarEsp(@RequestBody EspanolnputDto espanolnputDto, @PathVariable String palabra){
        return espanolService.updateEspanol(espanolnputDto,palabra);
    }

}
