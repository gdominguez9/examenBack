package com.example.pruebasExamen.Espanol.infrastructure.controller.dto.output;

import com.example.pruebasExamen.Espanol.domain.Espanol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspanolSimpleOutputDto {

    int id;
    String palabra;
    String descripcion;
    Date fechaAlta;
    Date fechaModificacion;

    public EspanolSimpleOutputDto(Espanol espanol){
        this.id = espanol.getId();
        this.palabra = espanol.getPalabra();
        this.descripcion = espanol.getDescripcion();
        this.fechaAlta = espanol.getFecha_alta();
        this.fechaModificacion = espanol.getFecha_modif();
    }

}
