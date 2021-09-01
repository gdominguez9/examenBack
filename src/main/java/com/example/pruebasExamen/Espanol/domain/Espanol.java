package com.example.pruebasExamen.Espanol.domain;

import com.example.pruebasExamen.Espanol.infrastructure.controller.dto.input.EspanolnputDto;
import com.example.pruebasExamen.Ingles.domain.Ingles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Espanol")
public class Espanol {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String palabra;

    @Column
    private String descripcion;

    @Column(nullable = false)
    private Date fecha_alta;

    @Column
    private Date fecha_modif;

    @OneToMany(mappedBy="espanol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingles> inglesList;

    public Espanol(EspanolnputDto espanolnputDto){
        if(espanolnputDto.getPalabra()!=null) this.palabra = espanolnputDto.getPalabra();
        if(espanolnputDto.getDescripcion()!=null) this.descripcion = espanolnputDto.getDescripcion();
        this.fecha_alta = new Date();
        this.fecha_modif = null;
    }

}
