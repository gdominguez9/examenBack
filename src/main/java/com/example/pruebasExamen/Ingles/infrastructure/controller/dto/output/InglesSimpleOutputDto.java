package com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output;

import com.example.pruebasExamen.Ingles.domain.Ingles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InglesSimpleOutputDto {

    private int id;
    private String palabra;
    private Date fechaAlta;
    private Date fechaModificacion;


    public InglesSimpleOutputDto(Ingles ingles){
        this.id = ingles.getId();
        this.palabra = ingles.getPalabra();
        this.fechaAlta = ingles.getFecha_alta();
        this.fechaModificacion = ingles.getFecha_modif();
    }

}
