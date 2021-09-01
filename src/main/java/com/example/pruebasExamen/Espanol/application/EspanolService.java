package com.example.pruebasExamen.Espanol.application;

import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output.EspanolOutputDto;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output.EspanolSimpleOutputDto;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.input.EspanolnputDto;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesSimpleOutputDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EspanolService {

    ResponseEntity<EspanolSimpleOutputDto> saveEspanol(EspanolnputDto espanolInputDto);

    ResponseEntity<EspanolOutputDto> getPalabraEspanol(String palabra);

    ResponseEntity<List<EspanolOutputDto>> getEspanol();

    ResponseEntity<EspanolSimpleOutputDto> deletePalabraEspanol(String palabra);

    ResponseEntity<InglesSimpleOutputDto> deleteEspanol();

    ResponseEntity<EspanolSimpleOutputDto> updateEspanol(EspanolnputDto espanolInputDto, String palabra);

}
