package com.example.pruebasExamen.Ingles.domain;

import com.example.pruebasExamen.Espanol.domain.Espanol;
import com.example.pruebasExamen.Espanol.infrastructure.repository.EspanolRepository;
import com.example.pruebasExamen.Ingles.infrastructure.controller.dto.input.InglesInputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Ingles")
public class Ingles {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String palabra;

    @Column(nullable = false)
    private Date fecha_alta;

    @Column
    private Date fecha_modif;

    //Relación con español
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_palabra_espanol")
    Espanol espanol;

    public Ingles(InglesInputDto inglesInputDto, EspanolRepository espanolRepository){
        if(inglesInputDto.getPalabra()!=null) this.palabra = inglesInputDto.getPalabra();
        this.espanol = espanolRepository.getByPalabra(inglesInputDto.getPalabraEspanol());
        this.fecha_alta = new Date();
        this.fecha_modif = null;
    }

}
