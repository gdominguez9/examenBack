package com.example.pruebasExamen.Ingles.infrastructure.controller.dto.output;

import com.example.pruebasExamen.Ingles.domain.Ingles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InglesOutputDto extends InglesSimpleOutputDto{

    //Campos que coge de entity
    private int id;
    private String palabra;
    private Date fechaAlta;
    private Date fechaModificacion;

    //Campos nuevos obtenidos a partir del id_palabra_espanol
    private String descripcion;
    private String palabraEspanol;

    public InglesOutputDto(Ingles ingles){
        this.id = ingles.getId();
        this.palabra = ingles.getPalabra();
        this.fechaAlta = ingles.getFecha_alta();
        this.fechaModificacion = ingles.getFecha_modif();
        this.palabraEspanol = ingles.getEspanol().getPalabra();
        this.descripcion = ingles.getEspanol().getDescripcion();
    }
}
