package com.example.pruebasExamen.Ingles.application;

import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.input.InglesInputDto;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesOutputDto;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output.InglesSimpleOutputDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InglesService {

    ResponseEntity<InglesOutputDto> saveIngles(InglesInputDto inglesInputDto);

    ResponseEntity<InglesOutputDto> getPalabraIngles(String palabra);

    ResponseEntity<List<InglesOutputDto>> getIngles();

    ResponseEntity<InglesSimpleOutputDto> deleteIngles();

    ResponseEntity<InglesSimpleOutputDto> deletePalabraIngles(String palabra);

    ResponseEntity<InglesOutputDto> updateIngles(InglesInputDto inglesInputDto, String palabra);

}
