package com.example.pruebasExamen.Ingles.application;

import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.input.InglesInputDto;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.RespuestaIngles;

public interface InglesService {

    RespuestaIngles saveIngles(InglesInputDto inglesInputDto);

    RespuestaIngles getPalabraIngles(String palabra);

    RespuestaIngles getIngles();

    RespuestaIngles deleteIngles();

    RespuestaIngles deletePalabraIngles(String palabra);

    RespuestaIngles updateIngles(InglesInputDto inglesInputDto, String palabra);

}
