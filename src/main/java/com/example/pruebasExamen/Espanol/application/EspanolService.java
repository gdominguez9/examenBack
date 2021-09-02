package com.example.pruebasExamen.Espanol.application;

import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.input.EspanolnputDto;
import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.RespuestaEspanol;

public interface EspanolService {

    RespuestaEspanol saveEspanol(EspanolnputDto espanolInputDto);

    RespuestaEspanol getPalabraEspanol(String palabra);

    RespuestaEspanol getEspanol();

    RespuestaEspanol deletePalabraEspanol(String palabra);

    RespuestaEspanol deleteEspanol();

    RespuestaEspanol updateEspanol(EspanolnputDto espanolInputDto, String palabra);

}
